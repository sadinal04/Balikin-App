package com.example.balikin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.balikin.adapter.LostItemsAdapter
import com.example.balikin.api.RetrofitClient
import com.example.balikin.model.LostItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardActivity : AppCompatActivity() {

    private lateinit var lostItemsRecyclerView: RecyclerView
    private lateinit var lostItemsAdapter: LostItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        // Menyiapkan RecyclerView untuk menampilkan barang hilang
        lostItemsRecyclerView = findViewById(R.id.lost_items_recycler_view)
        lostItemsRecyclerView.layoutManager = LinearLayoutManager(this)

        // Membuat adapter untuk RecyclerView
        lostItemsAdapter = LostItemsAdapter(listOf()) // Belum ada data
        lostItemsRecyclerView.adapter = lostItemsAdapter

        // Panggil API untuk mendapatkan data barang hilang
        getLostItems()

        // Menangani pemilihan item pada BottomNavigationView
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_nav)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    // Tindakan untuk navigasi ke halaman Home
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_profile -> {
                    // Buka halaman profil saat item "Profile" dipilih
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

    private fun getLostItems() {
        // Mendapatkan token dari SharedPreferences
        val sharedPref = getSharedPreferences("AppPreferences", MODE_PRIVATE)
        val token = sharedPref.getString("token", null)

        // Cek apakah token ada
        if (token == null) {
            Toast.makeText(this, "Token tidak ditemukan. Silakan login.", Toast.LENGTH_SHORT).show()
            Log.e("DashboardActivity", "Token tidak ditemukan di SharedPreferences.")
            return
        }

        // Log token yang digunakan untuk debugging
        Log.d("DashboardActivity", "Token: $token")

        // Panggil API untuk mendapatkan daftar barang hilang
        RetrofitClient.apiService.getLostItems("Bearer $token").enqueue(object : Callback<List<LostItem>> {
            override fun onResponse(call: Call<List<LostItem>>, response: Response<List<LostItem>>) {
                // Log status code dan body respons API untuk debugging
                Log.d("DashboardActivity", "Response code: ${response.code()}")
                if (response.isSuccessful) {
                    val lostItems = response.body()
                    if (lostItems != null) {
                        // Update RecyclerView dengan data barang hilang
                        lostItemsAdapter.updateItems(lostItems)
                    }
                } else {
                    // Log error jika response gagal
                    Log.e("API Error", "Response Error: ${response.code()} - ${response.message()}")
                    Toast.makeText(this@DashboardActivity, "Gagal memuat data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<LostItem>>, t: Throwable) {
                // Log error jika request gagal
                Log.e("API Error", "Failure: ${t.message}")
                Toast.makeText(this@DashboardActivity, "Terjadi kesalahan: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
