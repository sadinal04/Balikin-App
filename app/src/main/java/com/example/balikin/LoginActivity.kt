package com.example.balikin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.balikin.api.RetrofitClient
import com.example.balikin.model.LoginRequest
import com.example.balikin.model.ResponseLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Menggunakan findViewById untuk mengakses view
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        loginButton = findViewById(R.id.login_button)

        // Set onClick listener untuk login button
        loginButton.setOnClickListener {
            val emailText = email.text.toString()
            val passwordText = password.text.toString()

            // Validasi input email dan password
            if (emailText.isNotEmpty() && passwordText.isNotEmpty()) {
                val loginRequest = LoginRequest(emailText, passwordText)
                loginUser(loginRequest)
            } else {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Function to handle login API call
    private fun loginUser(loginRequest: LoginRequest) {
        RetrofitClient.apiService.login(loginRequest).enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()

                    // Log response body untuk debugging
                    if (loginResponse != null) {
                        if (loginResponse.token != null) {
                            // Successful login, save token and user info
                            saveToken(loginResponse.token)

                            Toast.makeText(
                                this@LoginActivity,
                                "Login Successful",
                                Toast.LENGTH_SHORT
                            ).show()

                            // Pindah ke MainActivity setelah login sukses
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish() // Menutup LoginActivity agar tidak bisa kembali dengan tombol back
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "Login Failed: No Token",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "Login Failed: Response Empty",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    // Jika response gagal, tampilkan pesan dan log response untuk debugging
                    Toast.makeText(
                        this@LoginActivity,
                        "Login Failed: ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                // Jika terjadi error pada API call
                Toast.makeText(this@LoginActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // Function to save token in SharedPreferences
    private fun saveToken(token: String) {
        val sharedPref = getSharedPreferences("AppPreferences", MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("token", token)
        editor.apply()
    }
}

