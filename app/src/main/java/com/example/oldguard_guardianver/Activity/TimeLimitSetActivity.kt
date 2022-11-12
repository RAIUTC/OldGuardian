package com.example.oldguard_guardianver.Activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.oldguard_guardianver.App
import com.example.oldguard_guardianver.HowIService
import com.example.oldguard_guardianver.Request.TimeLimitRequest
import com.example.oldguard_guardianver.databinding.ActivityTimeLimitSetBinding
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TimeLimitSetActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityTimeLimitSetBinding

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityTimeLimitSetBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.nextBtn.setOnClickListener {
//           var gson = GsonBuilder().setLenient().create()
//            var request = TimeLimitRequest(viewBinding.editMessage.text.toString().toLong())
//            val client = OkHttpClient.Builder().addInterceptor { chain ->
//                val newRequest: Request = chain.request().newBuilder()
//                    .addHeader("Authorization", "Bearer ${App.token_prefs.accessToken}")
//                    .build()
//                chain.proceed(newRequest)
//            }.build()
//
//            var retrofit = Retrofit.Builder()
//                .client(client)
//                .baseUrl("http://10.0.2.2:8080")
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build()
//            var server = retrofit.create(HowIService::class.java)
//            server.postTimeLimitRequest(request).enqueue(object : Callback<String> {
//                override fun onFailure(call: Call<String>, t: Throwable) {
//                }
//                override fun onResponse(call: Call<String>, response: Response<String>) {
//
//                }
//            })
            val intent = Intent(this, DegAndWindowActivity::class.java)
            startActivity(intent)
        }
    }
}