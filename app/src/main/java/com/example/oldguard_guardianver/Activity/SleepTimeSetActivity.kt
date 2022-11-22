package com.example.oldguard_guardianver.Activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.oldguard_guardianver.App
import com.example.oldguard_guardianver.Request.ClockRequest
import com.example.oldguard_guardianver.HowIService
import com.example.oldguard_guardianver.databinding.ActivitySleepTimeSetBinding
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalTime

/**   어르신 추가 시 수면시간 설정 화면   */
class SleepTimeSetActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySleepTimeSetBinding   //activity_sleep_time_set 화면과 연결

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivitySleepTimeSetBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.nextBtn.setOnClickListener {

//            var sleeptime: LocalTime = LocalTime.of(viewBinding.editStartHour.text.toString().toInt(),viewBinding.editStartMinute.text.toString().toInt())
//            var wakeuptime: LocalTime = LocalTime.of(viewBinding.editEndHour.text.toString().toInt(),viewBinding.editEndMinute.text.toString().toInt())
//            var request = ClockRequest( sleeptime.toString(),wakeuptime.toString())
//            var gson = GsonBuilder().setLenient().create()
//
//            Log.d("시간",sleeptime.toString())
//
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
//            server.postClockRequest(request).enqueue(object : Callback<String> {
//                override fun onFailure(call: Call<String>, t: Throwable) {
//                }
//                override fun onResponse(call: Call<String>, response: Response<String>) {
//                }
//            })

            //val intent = Intent(this, TimeLimitSetActivity::class.java)
            viewBinding.nextBtn.setOnClickListener {
                val intent = Intent(this, TimeLimitSetActivity::class.java)
                startActivity(intent)
            }
        }
    }
}