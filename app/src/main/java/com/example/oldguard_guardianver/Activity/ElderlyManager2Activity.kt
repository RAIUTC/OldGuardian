package com.example.oldguard_guardianver.Activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oldguard_guardianver.Adapter.ElderManagerRVAdapter
import com.example.oldguard_guardianver.App
import com.example.oldguard_guardianver.HowIService
import com.example.oldguard_guardianver.Request.AllRequest
import com.example.oldguard_guardianver.Request.GuestLoginRequest
import com.example.oldguard_guardianver.databinding.ActivityElderlyManager2Binding
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class ElderlyManager2Activity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityElderlyManager2Binding
    private lateinit var getResultText : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityElderlyManager2Binding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        //MainIntent에서 이름받아오기

        //여기 변수 이름을 elderMangerRVAdapterr가 아니라 elderInfoRVAdapter라고 적었듬

        val dataList : ArrayList<GuestLoginRequest> = arrayListOf()

        val elderManagerRVAdapter = ElderManagerRVAdapter(dataList)
        viewBinding.elderManagerRv.adapter = elderManagerRVAdapter
        //역순으로 (생성된 순) 출력
        val manager = LinearLayoutManager(this)
        manager.reverseLayout = true
        manager.stackFromEnd = true
        //LayoutManager 설정
        viewBinding.elderManagerRv.layoutManager = manager

        //viewBinding.elderName.text= intent.getStringExtra("name")

//        var request = AllRequest("guestName","name","phoneNumber")
//        var gson = GsonBuilder().setLenient().create()
//
//        var r = ""
//        val client = OkHttpClient.Builder().addInterceptor { chain ->
//            val newRequest: Request = chain.request().newBuilder()
//                .addHeader("Authorization", "Bearer ${App.token_prefs.accessToken}")
//                .build()
//            chain.proceed(newRequest)
//        }.build()
//        var retrofit = Retrofit.Builder()
//            .client(client)
//            .baseUrl("http://10.0.2.2:8080")
//            .addConverterFactory(ScalarsConverterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//        var server = retrofit.create(HowIService::class.java)
//        server.getLoginRequest(request,request,request).enqueue(object : Callback<AllRequest> {
//            override fun onFailure(call: Call<AllRequest>, t: Throwable) {
//            }
//            override fun onResponse(call: Call<AllRequest>, response: Response<AllRequest>) {
//                Log.d("성공", response.body().toString())
//                request.guestName = response.body()?.guestName.toString()
//                request.name = response.body()?.name.toString()
//                request.phoneNumber=response.body()?.phoneNumber.toString()
//                Log.d("게스트이름",request.guestName)
//                Log.d("이름",request.name)
//                Log.d("전화번호",request.phoneNumber)
//            }
//        })


        dataList.apply {
            add(GuestLoginRequest("로그인코드","어르신1"))
            add(GuestLoginRequest("로그인코드","어르신2"))
            add(GuestLoginRequest("로그인코드","어르신3"))
            add(GuestLoginRequest("로그인코드","어르신4"))
            add(GuestLoginRequest("로그인코드","어르신5"))
            add(GuestLoginRequest("로그인코드","어르신6"))
            add(GuestLoginRequest("로그인코드","어르신7"))
        }

        //dataList가 비어있지 않으면 text 보이지 않게 하기
        if(dataList.isNotEmpty()) {
            viewBinding.letsStartText.visibility = View.INVISIBLE
        }
        var intent : Intent

        elderManagerRVAdapter.setOnItemClickListener(object : ElderManagerRVAdapter.OnItemClickListener {
            override fun onItemClick(view : View, data : GuestLoginRequest, position : Int) {
                intent = Intent(this@ElderlyManager2Activity, ElderInfoActivity::class.java)
//                intent.putExtra("guestName",request.guestName)
//                intent.putExtra("name",request.name)
    //                intent.putExtra("phoneNumber",request.phoneNumber)
                    startActivity(intent)
                }
            })

        //Adapter에서 삭제 정보 처리 완료
//        //어르신 삭제 버튼 눌렀을 때
//       viewBinding.elderDeleteBtn.setOnClickListener {
//            val builder = AlertDialog.Builder(this)
//                .setTitle("경고")
//                .setTitle("어르신 정보를 삭제하시겠습니까?\n삭제 기록에서 복구할 수 있습니다.\n")
//                .setNegativeButton("취소",
//                    DialogInterface.OnClickListener { dialog, which ->
//                        //아무 활동도 하지 않음. 코드 작성 필요X
//                    })
//                .setPositiveButton("확인",
//                    DialogInterface.OnClickListener { dialog, which ->
//                        //삭제하는 코드 작성
//                    })
//        }

//        //어르신 상세 정보 버튼 눌렀을 때
//        viewBinding.elderBtn.setOnClickListener {
//            val intent = Intent(this, ElderInfoActivity::class.java);
//            intent.putExtra("guestName",request.guestName)
//            intent.putExtra("name",request.name)
//            intent.putExtra("phoneNumber",request.phoneNumber)
//            startActivity(intent)
//        }

        //추가하기 버튼 눌렀을 때
        viewBinding.addBtn.setOnClickListener {
            intent = Intent(this, AuthCodeActivity::class.java)
            startActivity(intent)
        }
    }
}


