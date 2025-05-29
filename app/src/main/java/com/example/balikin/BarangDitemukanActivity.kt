package com.example.balikin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class BarangDitemukanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.barang_ditemukan)

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_nav)
        setupBottomNavigation(this, bottomNav)
    }
}
