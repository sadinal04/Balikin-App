package com.example.balikin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ReportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Panggil layout yang sudah kamu buat
        setContentView(R.layout.lapor_temuan)

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_nav)
        setupBottomNavigation(this, bottomNav)
    }
}
