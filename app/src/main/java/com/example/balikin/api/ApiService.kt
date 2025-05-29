package com.example.balikin.api

import com.example.balikin.model.LoginRequest
import com.example.balikin.model.RegisterRequest
import com.example.balikin.model.ResponseLogin
import com.example.balikin.model.ResponseRegister
import com.example.balikin.model.Post  // Import model Post
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    // Endpoint untuk login
    @POST("api/auth/login")
    fun login(@Body loginRequest: LoginRequest): Call<ResponseLogin>

    // Endpoint untuk register
    @POST("api/auth/register")
    fun register(@Body registerRequest: RegisterRequest): Call<ResponseRegister>

    // Endpoint untuk mengambil posts (gunakan GET)
    @GET("api/posts")  // Sesuaikan dengan endpoint backend kamu
    fun getPosts(): Call<List<Post>>  // Mengembalikan daftar Post
}
