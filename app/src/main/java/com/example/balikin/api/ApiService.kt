package com.example.balikin.api

import com.example.balikin.model.LoginRequest
import com.example.balikin.model.LostItem
import com.example.balikin.model.RegisterRequest
import com.example.balikin.model.ResponseLogin
import com.example.balikin.model.ResponseRegister
import com.example.balikin.model.Post  // Import model Post
import com.example.balikin.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    // Endpoint untuk login
    @POST("api/auth/login")
    fun login(@Body loginRequest: LoginRequest): Call<ResponseLogin>

    // Endpoint untuk register
    @POST("api/auth/register")
    fun register(@Body registerRequest: RegisterRequest): Call<ResponseRegister>

    // Endpoint untuk mengambil profil pengguna
    @GET("api/auth/profile")
    fun getProfile(@Header("Authorization") token: String): Call<User>  // Menambahkan @Header pada token

    // Endpoint untuk mendapatkan daftar barang hilang
    @GET("api/lost-items")
    fun getLostItems(@Header("Authorization") token: String): Call<List<LostItem>>  // Mengambil daftar barang hilang

    @GET("lost-items/{id}")
    fun getLostItemDetail(
        @Header("Authorization") token: String,
        @Path("id") itemId: String
    ): Call<LostItem>

    // Endpoint untuk mengambil posts (gunakan GET)
    @GET("api/posts")  // Sesuaikan dengan endpoint backend kamu
    fun getPosts(): Call<List<Post>>  // Mengembalikan daftar Post
}
