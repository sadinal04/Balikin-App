package com.example.balikin

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

fun setupBottomNavigation(activity: Activity, bottomNavigationView: BottomNavigationView) {
    bottomNavigationView.setOnItemSelectedListener { menuItem ->
        val currentActivity = activity::class.java // Nama class activity sekarang
        when (menuItem.itemId) {
            R.id.nav_home -> {
                if (currentActivity != DashboardActivity::class.java) {
                    activity.startActivity(Intent(activity, DashboardActivity::class.java))
                    activity.finish()
                }
                true
            }
            R.id.nav_profile -> {
                if (currentActivity != ProfileActivity::class.java) {
                    activity.startActivity(Intent(activity, ProfileActivity::class.java))
                    activity.finish()
                }
                true
            }
            R.id.nav_add -> {
                if (currentActivity != ReportActivity::class.java) {
                    activity.startActivity(Intent(activity, ReportActivity::class.java))
                    activity.finish()
                }
                true
            }
            R.id.nav_search -> {
                if (currentActivity != BarangDitemukanActivity::class.java) {
                    activity.startActivity(Intent(activity, BarangDitemukanActivity::class.java))
                    activity.finish()
                }
                true
            }
            else -> false
        }
    }
}
