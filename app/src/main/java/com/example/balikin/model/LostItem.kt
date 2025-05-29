package com.example.balikin.model

import com.google.gson.annotations.SerializedName

data class LostItem(
    @SerializedName("_id")
    val id: String,  // ID item

    @SerializedName("name")
    val name: String,  // Nama barang

    @SerializedName("description")
    val description: String,  // Deskripsi barang

    @SerializedName("location")
    val location: String,  // Lokasi barang

    @SerializedName("image")
    val image: String,  // Gambar barang dalam base64

    @SerializedName("status")
    val status: String,  // Status barang (unclaimed/claimed)

    @SerializedName("timestamp")
    val timestamp: String  // Waktu barang hilang
)
