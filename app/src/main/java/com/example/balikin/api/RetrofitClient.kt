package com.example.balikin.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://balikin-backend.vercel.app"  // Ganti dengan URL backend kamu

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())  // Untuk konversi JSON ke objek Kotlin
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}
