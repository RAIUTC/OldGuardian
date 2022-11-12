package com.example.oldguard_guardianver.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.oldguard_guardianver.App
import com.example.oldguard_guardianver.Request.GuestLoginRequest
import com.example.oldguard_guardianver.HowIService
import com.example.oldguard_guardianver.databinding.ActivityAuthCodeBinding
import com.example.oldguard_guardianver.intent.ContactIntent
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AuthCodeActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityAuthCodeBinding

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityAuthCodeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.nextBtn.setOnClickListener {
            var request = GuestLoginRequest(viewBinding.editLoginCode.text.toString(),viewBinding.editElderName.text.toString())
            var gson = GsonBuilder().setLenient().create()
            var address = viewBinding.editGuardianAddress.text.toString()       //입력받은 주소지 저장

            val client = OkHttpClient.Builder().addInterceptor { chain ->
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${App.token_prefs.accessToken}")
                    .build()
                chain.proceed(newRequest)
            }.build()

            var retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl("http://10.0.2.2:8080")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            var server = retrofit.create(HowIService::class.java)
            server.postGuestLoginRequest(request).enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {

                }
                override fun onResponse(call: Call<String>, response: Response<String>) {

                }
            })
            val intent = Intent(this, ContactIntent::class.java)
            intent.flags  = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

    }
}