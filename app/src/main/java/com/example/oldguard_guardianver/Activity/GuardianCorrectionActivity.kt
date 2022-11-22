package com.example.oldguard_guardianver.Activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.oldguard_guardianver.App
import com.example.oldguard_guardianver.Request.CorrectionRequest
import com.example.oldguard_guardianver.HowIService
import com.example.oldguard_guardianver.databinding.ActivityGuardianCorrectionBinding
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**   어르신 관리에서 보호자 정보 수정 화면(삭제 저장 가능)   */
class GuardianCorrectionActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityGuardianCorrectionBinding //activity_guardian_correction화면과 연결

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        var position = intent.getIntExtra("position",0) //꼭 위에 위치시키기

        var intent : Intent
        viewBinding = ActivityGuardianCorrectionBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //삭제 버튼 눌렀을 때
        viewBinding.deleteBtn.setOnClickListener {
            //삭제 확인 창
            val builder = AlertDialog.Builder(this)
                .setTitle("경고")
                .setTitle("보호자 정보를 삭제하시겠습니까?\n")
                .setNegativeButton("취소",
                    DialogInterface.OnClickListener { dialog, which ->
                        //아무런 행동 없음.
                    })
                .setPositiveButton("확인",
                    DialogInterface.OnClickListener { dialog, which ->
                        intent = Intent(this, ElderInfoActivity::class.java)
                        intent.putExtra("position", position)
                        setResult(DELETE, intent)
                        finish()    //Activity 종료
                    })
            builder.show()
        }

        //저장 버튼 눌렀을 때
        viewBinding.saveBtn.setOnClickListener {
            var name = viewBinding.editCorrectName.toString()
            var contact = viewBinding.editCorrectNumber.toString()
            var request = CorrectionRequest(name,contact)
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
            server.postCorrectionRequest(request).enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.e("어르신 등록 실패", "${t.localizedMessage}")
                }
                override fun onResponse(call: Call<String>, response: Response<String>) {

                }
            })
            val intent = Intent(this, ElderInfoActivity::class.java)
            intent.putExtra("position", position)
            setResult(FIX, intent)
            finish()
        }
    }
    companion object{
        const val DELETE = 1001 //삭제했을 때 resultCode
        const val FIX = 1002    //수정했을 때 resultCode
    }
}