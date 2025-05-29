package com.example.balikin.model

data class ResponseLogin(
    val token: String?,
    val user: User?  // User adalah objek yang berisi informasi pengguna
)
