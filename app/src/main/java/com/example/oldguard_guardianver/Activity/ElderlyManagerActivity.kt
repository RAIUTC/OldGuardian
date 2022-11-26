package com.example.oldguard_guardianver.Activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oldguard_guardianver.Adapter.ElderInfoRVAdapter
import com.example.oldguard_guardianver.Adapter.ElderManagerRVAdapter
import com.example.oldguard_guardianver.App
import com.example.oldguard_guardianver.HowIService
import com.example.oldguard_guardianver.Request.*
import com.example.oldguard_guardianver.databinding.ActivityElderlyManagerBinding
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**   관리하는 어르신을 보여주는 메인 화면   */
class ElderlyManagerActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityElderlyManagerBinding //activity_elder_manager과 연결
    private lateinit var getResultText : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityElderlyManagerBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val dataList : ArrayList<GuestLoginRequest> = arrayListOf()

        val elderManagerRVAdapter = ElderManagerRVAdapter(dataList)
        viewBinding.elderManagerRv.adapter = elderManagerRVAdapter
        //역순으로 (생성된 순) 출력
        val manager = LinearLayoutManager(this)
        manager.reverseLayout = true
        manager.stackFromEnd = true
        //LayoutManager 설정
        viewBinding.elderManagerRv.layoutManager = manager

        var gson = GsonBuilder().setLenient().create()

        var r = ""
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

        var tempName = ""
        var tempContacts = Contact(0L,"contact","name")
        var tempMessageTime = 0L
        var tempCallTime = 0L
        var tempEmergencyTime = 0L
        var tempSleepTime = ""
        var tempEndTime = ""


        server.getAllResponse(0L).enqueue(object : Callback<GuestResponse> {
            override fun onFailure(call: Call<GuestResponse>, t: Throwable) {
                Log.e("실패",t.toString())
            }
            override fun onResponse(call: Call<GuestResponse>, response: Response<GuestResponse>) {
                Log.d("성공", response.body().toString())
                Log.d("contact", response.body()?.guestName.toString())
//                if(response.body().toString() != null){
//                    Log.d("if실행", "왜?")
//                    tempName = response.body()?.guestName.toString()
//                    tempContacts= response.body()?.contacts?.get(0)!!
//                    tempMessageTime = response.body()?.messageTime!!
//                    tempCallTime = response.body()?.callTime!!
//                    tempEmergencyTime = response.body()?.emergencyTime!!
//                    tempSleepTime = response.body()?.sleepStartTime.toString()
//                    tempEndTime = response.body()?.sleepEndTime.toString()
//                }
            }
        })

        //추가하기 버튼 눌렀을 때
        viewBinding.addBtn.setOnClickListener {
            val intent = Intent(this, AuthCodeActivity::class.java)
            startActivity(intent)
        }

        //기록 버튼을 눌렀을 때
        viewBinding.recordBtn.setOnClickListener {
            val intent = Intent(this, RecordActivity::class.java)
            getResultText.launch(intent)
        }

        //기록 버튼을 눌렀을 떄는 intent.이 아니라 result.으로 전달값을 받는다
        //이 범위 안에서만
        getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        )
        {
                result ->
            //기록 activity를 읽기만 했을 때
            if (result.resultCode == READ) {
                //화면만 돌아옴 따로 추가할 내용 X
            }
            //기록 activity를 읽고 삭제된 목록을 복구했을 떄
            else if (result.resultCode == WRITE) {
                //RecyclerView add 사용해서 추가
            } else {
                Log.d("에러메시지", "resultCode값 없음")
            }
        }


        //예시 어르신 list
        dataList.apply {
//            add(GuestLoginRequest("로그인코드","강순자"))
//            add(GuestLoginRequest("로그인코드","김영호"))
//            add(GuestLoginRequest("로그인코드","박정남"))
//            add(GuestLoginRequest("로그인코드","김종수"))
//            add(GuestLoginRequest("로그인코드","서영자"))
//            add(GuestLoginRequest("로그인코드","문현숙"))
//            add(GuestLoginRequest("로그인코드","차영일"))
        }
//                새로운 데이터 추가 시 add, 수정 시 set, 삭제 시 removeAt

        //dataList가 비어있지 않으면 text 보이지 않게 하기
        if(dataList.isNotEmpty()) {
            viewBinding.letsStartText.visibility = View.INVISIBLE
        }
        var intent : Intent

        //아이템 전체를 눌렀을 떄
        elderManagerRVAdapter.setOnItemClickListener(object : ElderManagerRVAdapter.OnItemClickListener {
            override fun onItemClick(view : View, data : GuestLoginRequest, position : Int) {
                intent = Intent(this@ElderlyManagerActivity, ElderInfoActivity::class.java)
//                intent.putExtra("guestName",request.guestName)
//                intent.putExtra("name",request.name)
//                intent.putExtra("phoneNumber",request.phoneNumber)
                startActivity(intent)
            }
        })

        //아이템에서 쓰레기통 버튼을 눌렀을 때
        elderManagerRVAdapter.setOnBtnClickListener(object : ElderManagerRVAdapter.OnBtnClickListener {
            override fun onBtnClick(view: View, data: GuestLoginRequest, position: Int) {
                val builder = AlertDialog.Builder(this@ElderlyManagerActivity)
                .setTitle("경고")
                .setTitle("어르신 정보를 삭제하시겠습니까?\n삭제 기록에서 복구할 수 있습니다.\n")
                .setNegativeButton("취소",
                    DialogInterface.OnClickListener { dialog, which ->
                        //아무 활동도 하지 않음. 코드 작성 필요X
                    })
                .setPositiveButton("확인",
                    DialogInterface.OnClickListener { dialog, which ->
                        dataList.removeAt(position)
                        elderManagerRVAdapter.notifyItemRemoved(position)
                    })
                builder.show()
            }
         })
    }
    companion object{
        const val READ = 1001
        const val WRITE = 1002
    }
}