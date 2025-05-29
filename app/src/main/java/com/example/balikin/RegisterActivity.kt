package com.example.balikin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.balikin.api.RetrofitClient
import com.example.balikin.model.RegisterRequest
import com.example.balikin.model.ResponseRegister
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var name: EditText
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Mengakses view dari layout
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        name = findViewById(R.id.name)
        registerButton = findViewById(R.id.register_button)

        // Set onClick listener untuk register button
        registerButton.setOnClickListener {
            val emailText = email.text.toString()
            val passwordText = password.text.toString()
            val nameText = name.text.toString()

            // Validasi input
            if (emailText.isNotEmpty() && passwordText.isNotEmpty() && nameText.isNotEmpty()) {
                val registerRequest = RegisterRequest(emailText, passwordText, nameText)
                registerUser(registerRequest)
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Fungsi untuk meng-handle permintaan register
    private fun registerUser(registerRequest: RegisterRequest) {
        RetrofitClient.apiService.register(registerRequest).enqueue(object : Callback<ResponseRegister> {
            override fun onResponse(call: Call<ResponseRegister>, response: Response<ResponseRegister>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@RegisterActivity, "Registration Successful", Toast.LENGTH_SHORT).show()

                    // Pindah ke LoginActivity setelah berhasil register
                    // Anda bisa menggunakan Intent untuk berpindah activity
                    finish() // Atau, jika ingin kembali ke activity login, ganti dengan:
                    // val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    // startActivity(intent)
                } else {
                    // Jika registrasi gagal
                    Toast.makeText(this@RegisterActivity, "Registration Failed: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                // Jika terjadi error
                Toast.makeText(this@RegisterActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
