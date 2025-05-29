package com.example.balikin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Pindah ke LoginActivity setelah MainActivity dijalankan
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // Menyelesaikan MainActivity agar tidak kembali ke sini
    }
}
