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
        var tempContacts0 = Contact(0L,"contact","name")
        var tempContacts02 = Contact(1L,"contact","name")
        var tempContacts03 = Contact(2L,"contact","name")
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
                if(response.body() != null){
                    tempName = response.body()?.guestName.toString()
                    if(response.body()?.contacts?.size != 0) {
                        if (response.body()?.contacts?.get(0) != null) {
                            tempContacts0 = response.body()?.contacts?.get(0)!!
                        }
                        if (response.body()?.contacts?.get(1) != null) {
                            Log.d("1번 보호자", response.body()?.contacts?.get(1)!!.toString())
                            tempContacts02 = response.body()?.contacts?.get(1)!!
                        }
                        if (response.body()?.contacts?.get(2) != null) {
                            tempContacts03 = response.body()?.contacts?.get(2)!!
                        }
                    }
                    tempMessageTime = response.body()?.messageTime!!
                    tempCallTime = response.body()?.callTime!!
                    tempEmergencyTime = response.body()?.emergencyTime!!
                    tempSleepTime = response.body()?.sleepStartTime.toString()
                    tempEndTime = response.body()?.sleepEndTime.toString()

                    dataList.apply {
                        add(GuestLoginRequest("ABCDEF", tempName))
                    }
                    elderManagerRVAdapter.notifyItemInserted(elderManagerRVAdapter.itemCount)
                    if(dataList.isNotEmpty()) {
                        viewBinding.letsStartText.visibility = View.INVISIBLE
                    }
                }
            }
        })
        var tempName1 = ""
        var tempContacts1 = Contact(0L,"contact","name")
        var tempContacts12 = Contact(1L,"contact","name")
        var tempContacts13 = Contact(2L,"contact","name")
        var tempMessageTime1 = 0L
        var tempCallTime1 = 0L
        var tempEmergencyTime1 = 0L
        var tempSleepTime1 = ""
        var tempEndTime1 = ""
        server.getAllResponse(1L).enqueue(object : Callback<GuestResponse> {
            override fun onFailure(call: Call<GuestResponse>, t: Throwable) {
                Log.e("실패",t.toString())
            }
            override fun onResponse(call: Call<GuestResponse>, response: Response<GuestResponse>) {
                Log.d("성공", response.body().toString())
                Log.d("contact", response.body()?.guestName.toString())
                if(response.body() != null){
                    tempName1 = response.body()?.guestName.toString()
                    if(response.body()?.contacts?.size != 0) {
                        if (response.body()?.contacts?.get(0)!! != null){
                            tempContacts1= response.body()?.contacts?.get(0)!!
                        }
                        if (response.body()?.contacts?.get(1)!! != null) {
                            tempContacts12= response.body()?.contacts?.get(1)!!
                        }
                        if (response.body()?.contacts?.get(2)!! != null) {
                            tempContacts13= response.body()?.contacts?.get(2)!!
                        }
                    }
                    tempMessageTime1 = response.body()?.messageTime!!
                    tempCallTime1 = response.body()?.callTime!!
                    tempEmergencyTime1 = response.body()?.emergencyTime!!
                    tempSleepTime1 = response.body()?.sleepStartTime.toString()
                    tempEndTime1 = response.body()?.sleepEndTime.toString()

                    dataList.apply {
                        add(GuestLoginRequest("ABCDEF", tempName1))
                    }
                    elderManagerRVAdapter.notifyItemInserted(elderManagerRVAdapter.itemCount)
                    if(dataList.isNotEmpty()) {
                        viewBinding.letsStartText.visibility = View.INVISIBLE
                    }
                }
            }
        })

        var tempName2 = ""
        var tempContacts2 = Contact(0L,"contact","name")
        var tempContacts22 = Contact(1L,"contact","name")
        var tempContacts23 = Contact(2L,"contact","name")
        var tempMessageTime2 = 0L
        var tempCallTime2 = 0L
        var tempEmergencyTime2 = 0L
        var tempSleepTime2 = ""
        var tempEndTime2 = ""
        server.getAllResponse(2L).enqueue(object : Callback<GuestResponse> {
            override fun onFailure(call: Call<GuestResponse>, t: Throwable) {
                Log.e("실패",t.toString())
            }
            override fun onResponse(call: Call<GuestResponse>, response: Response<GuestResponse>) {
                Log.d("성공", response.body().toString())
                Log.d("contact", response.body()?.guestName.toString())
                if(response.body() != null){
                    tempName2 = response.body()?.guestName.toString()
                    if(response.body()?.contacts?.size != 0) {
                        if (response.body()?.contacts?.get(0)!! != null){
                            tempContacts2= response.body()?.contacts?.get(0)!!
                        }
                        if (response.body()?.contacts?.get(1)!! != null) {
                            tempContacts22= response.body()?.contacts?.get(1)!!
                        }
                        if (response.body()?.contacts?.get(2)!! != null) {
                            tempContacts23= response.body()?.contacts?.get(2)!!
                        }
                    }
                    tempMessageTime2 = response.body()?.messageTime!!
                    tempCallTime2 = response.body()?.callTime!!
                    tempEmergencyTime2 = response.body()?.emergencyTime!!
                    tempSleepTime2 = response.body()?.sleepStartTime.toString()
                    tempEndTime2 = response.body()?.sleepEndTime.toString()

                    dataList.apply {
                        add(GuestLoginRequest("ABCDEF", tempName2))
                    }
                    elderManagerRVAdapter.notifyItemInserted(elderManagerRVAdapter.itemCount)
                    if(dataList.isNotEmpty()) {
                        viewBinding.letsStartText.visibility = View.INVISIBLE
                    }
                }
            }
        })
        var tempName3 = ""
        var tempContacts3 = Contact(0L,"contact","name")
        var tempContacts32 = Contact(1L,"contact","name")
        var tempContacts33 = Contact(2L,"contact","name")
        var tempMessageTime3 = 0L
        var tempCallTime3 = 0L
        var tempEmergencyTime3 = 0L
        var tempSleepTime3 = ""
        var tempEndTime3 = ""
        server.getAllResponse(3L).enqueue(object : Callback<GuestResponse> {
            override fun onFailure(call: Call<GuestResponse>, t: Throwable) {
                Log.e("실패",t.toString())
            }
            override fun onResponse(call: Call<GuestResponse>, response: Response<GuestResponse>) {
                Log.d("성공", response.body().toString())
                Log.d("contact", response.body()?.guestName.toString())
                if(response.body() != null){
                    tempName3 = response.body()?.guestName.toString()
                    if(response.body()?.contacts?.size != 0) {
                        if (response.body()?.contacts?.get(0)!! != null){
                            tempContacts3= response.body()?.contacts?.get(0)!!
                        }
                        if (response.body()?.contacts?.get(1)!! != null) {
                            tempContacts32= response.body()?.contacts?.get(1)!!
                        }
                        if (response.body()?.contacts?.get(2)!! != null) {
                            tempContacts33= response.body()?.contacts?.get(2)!!
                        }
                    }
                    tempMessageTime3 = response.body()?.messageTime!!
                    tempCallTime3 = response.body()?.callTime!!
                    tempEmergencyTime3 = response.body()?.emergencyTime!!
                    tempSleepTime3 = response.body()?.sleepStartTime.toString()
                    tempEndTime3 = response.body()?.sleepEndTime.toString()

                    dataList.apply {
                        add(GuestLoginRequest("ABCDEF", tempName3))
                    }
                    elderManagerRVAdapter.notifyItemInserted(elderManagerRVAdapter.itemCount)
                    if(dataList.isNotEmpty()) {
                        viewBinding.letsStartText.visibility = View.INVISIBLE
                    }
                }
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

//        dataList.apply {
//            add(GuestLoginRequest("로그인코드","강순자"))
//            add(GuestLoginRequest("로그인코드","김영호"))
//            add(GuestLoginRequest("로그인코드","박정남"))
//            add(GuestLoginRequest("로그인코드","김종수"))
//            add(GuestLoginRequest("로그인코드","서영자"))
//            add(GuestLoginRequest("로그인코드","문현숙"))
//            add(GuestLoginRequest("로그인코드","차영일"))
//        }
//                새로운 데이터 추가 시 add, 수정 시 set, 삭제 시 removeAt

        //dataList가 비어있지 않으면 text 보이지 않게 하기
        if(dataList.isNotEmpty()) {
            viewBinding.letsStartText.visibility = View.INVISIBLE
        }
        var intent : Intent

        //탈퇴하기 버튼 눌렀을 때
        viewBinding.withdraw.setOnClickListener() {
            val builder = AlertDialog.Builder(this)
                .setTitle("경고")
                .setTitle("정말 탈퇴하시겠습니까?\n모든 데이터가 삭제되고,\n 복구할 수 없습니다.\n\n")
                .setNegativeButton("취소",
                    DialogInterface.OnClickListener { dialog, which ->
                        //아무런 행동 없음.
                    })
                .setPositiveButton("확인",
                    DialogInterface.OnClickListener { dialog, which ->
                        //여기에 전체 delete하는 기능 구현하기
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
                        var server1 = retrofit.create(HowIService::class.java)

                        server1.userMembershipWithdrawal().enqueue(object : Callback<String> {
                            override fun onFailure(call: Call<String>, t: Throwable) {
                                Log.e("실패",t.toString())
                            }
                            override fun onResponse(call: Call<String>, response: Response<String>) {
                                Log.d("성공", response.body().toString())
                            }
                        })
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
//                        finish()    //Activity 종료
                    })
            builder.show()
        }

        //아이템 전체를 눌렀을 떄
        elderManagerRVAdapter.setOnItemClickListener(object : ElderManagerRVAdapter.OnItemClickListener {
            override fun onItemClick(view : View, data : GuestLoginRequest, position : Int) {
                intent = Intent(this@ElderlyManagerActivity, ElderInfoActivity::class.java)
                intent.putExtra("guestName",tempName)
                intent.putExtra("contact",tempContacts0.contact)
                intent.putExtra("contact1",tempContacts02.contact)
                intent.putExtra("contact2",tempContacts03.contact)
                intent.putExtra("name",tempContacts0.name)
                intent.putExtra("name1",tempContacts02.name)
                intent.putExtra("name2",tempContacts03.name)
                intent.putExtra("messageTime",tempMessageTime)
                intent.putExtra("callTime",tempCallTime)
                intent.putExtra("emergencyTime",tempEmergencyTime)
                intent.putExtra("sleepTime",tempSleepTime.substring(0,5))
                intent.putExtra("endTime",tempEndTime.substring(0,5))
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
                        var request1 = DeleteGuestInfoRequest(0L)
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

                        server.deleteGuestInfo(request1).enqueue(object : Callback<String> {
                            override fun onFailure(call: Call<String>, t: Throwable) {
                                Log.e("실패",t.toString())
                            }
                            override fun onResponse(call: Call<String>, response: Response<String>) {
                                Log.d("성공", response.body().toString())
                            }
                        })
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