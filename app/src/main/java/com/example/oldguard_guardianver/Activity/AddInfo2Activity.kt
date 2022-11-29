package com.example.oldguard_guardianver.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.oldguard_guardianver.App
import com.example.oldguard_guardianver.HowIService
import com.example.oldguard_guardianver.Request.AddInfoRequest
import com.example.oldguard_guardianver.databinding.ActivityAddInfo2Binding
import com.example.oldguard_guardianver.intent.PatternIntent
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**   두번째 어르신 추가 화면   */
class AddInfo2Activity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityAddInfo2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //activity_add_info2 와 연결
        viewBinding = ActivityAddInfo2Binding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val extras = intent.extras

        //연락처 추가하기 버튼 눌렀을 때
        viewBinding.addNumberBtn.setOnClickListener {
            val intent = Intent(this, AddInfo3Activity::class.java)
            startActivity(intent)
        }

        //다음단계 버튼 눌렀을 때
        viewBinding.nextBtn.setOnClickListener() {
            var name = viewBinding.editGuardianName2.text.toString()
            var number = viewBinding.editGuardianNumber2.text.toString()
            var request = AddInfoRequest(name,1L, number)
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
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            var server = retrofit.create(HowIService::class.java)
            server.postAddInfoRequest(request).enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.e("실패",t.toString())
                }
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Log.d("성공",response.body().toString())
                }
            })

            val intent = Intent(this, PatternIntent::class.java)
            startActivity(intent)
        }
    }
}