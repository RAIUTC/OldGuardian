package com.example.oldguard_guardianver.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.oldguard_guardianver.*
import com.example.oldguard_guardianver.Request.AddInfoRequest
import com.example.oldguard_guardianver.databinding.ActivityAddInfoBinding
import com.example.oldguard_guardianver.intent.PatternIntent
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddInfoActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityAddInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityAddInfoBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val extras = intent.extras

        //연락처 추가하기 버튼 눌렀을 때
        viewBinding.addNumberBtn.setOnClickListener {
            val intent = Intent(this, AddInfo2Activity::class.java)
            startActivity(intent)
        }

        //다음단계 버튼 눌렀을 때
        viewBinding.nextBtn.setOnClickListener() {
            var name = viewBinding.editGuardianName1.text.toString()
            var number = viewBinding.editGuardianNumber1.text.toString()
            var request = AddInfoRequest(name,number)
            var gson = GsonBuilder().setLenient().create()


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
            server.postAddInfoRequest(request).enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                }
                override fun onResponse(call: Call<String>, response: Response<String>) {

                }
            })

            //연락처 추가하기 버튼 눌렀을 때
            viewBinding.addNumberBtn.setOnClickListener() {
                val intent = Intent(this, AddInfo2Activity::class.java)
                startActivity(intent)
            }

            //다음단계 버튼 눌렀을 때
            viewBinding.nextBtn.setOnClickListener() {
                val intent = Intent(this, PatternIntent::class.java)
                startActivity(intent)
            }
        }
    }
}