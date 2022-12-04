package com.example.oldguard_guardianver.Activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.oldguard_guardianver.Activity.ElderInfoActivity.Companion.FIX
import com.example.oldguard_guardianver.Adapter.ElderInfoRVAdapter
import com.example.oldguard_guardianver.App
import com.example.oldguard_guardianver.HowIService
import com.example.oldguard_guardianver.Request.AddInfoRequest
import com.example.oldguard_guardianver.Request.ContactResponse
import com.example.oldguard_guardianver.databinding.ActivityElderInfoBinding
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.time.LocalTime

/**  어르신 관리에서 메인 다음 페이지(선택한 어르신의 보호자들, 생활패턴등 보여주는 화면)   */
class ElderInfoActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityElderInfoBinding      //activity_elder_info 화면과 연결
    private lateinit var getResultGuardian : ActivityResultLauncher<Intent>
    private lateinit var getResultLivingPT : ActivityResultLauncher<Intent>
    private lateinit var getResultDAW : ActivityResultLauncher<Intent>
    val dataList : ArrayList<AddInfoRequest> = arrayListOf()

    val elderInfoRVAdapter = ElderInfoRVAdapter(dataList)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityElderInfoBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        var intent1: Intent
        //Log.d("guestNameIntent",intent.getStringExtra("guestName").toString())
        var data = intent

        viewBinding.elderName.text= data.getStringExtra("guestName")
//        viewBinding.guardianName1.text = data.getStringExtra("contact")
//        viewBinding.guardianNumber1.text = data.getStringExtra("name")
        viewBinding.elderMessageTime.text = data.getLongExtra("messageTime",0).toString()
        viewBinding.elderCallTime.text = data.getLongExtra("callTime",0).toString()
        viewBinding.elderSosTime.text = data. getLongExtra("emergencyTime",0).toString()
        viewBinding.elderStartHour.text = data.getStringExtra("sleepTime")
        viewBinding.elderEndHour.text = data.getStringExtra("endTime")

        viewBinding.rvElderInfo.adapter = elderInfoRVAdapter

        val manager = LinearLayoutManager(this)
        viewBinding.rvElderInfo.layoutManager = manager

        //test용 추가. 데이터 관리할 때 변경 필요
        dataList.apply {
//            add(AddInfoRequest("김복원", 0L,"01077437539"))
//            add(AddInfoRequest("김복투", 1L,"01077437539"))
//            add(AddInfoRequest("김복쓰리", 2L, "01077437539"))
        }
        if(!data.getStringExtra("name").equals("name")) {
            dataList.apply {
                add(AddInfoRequest(data.getStringExtra("name"), 0L, data.getStringExtra("contact")))
            }
            elderInfoRVAdapter.notifyItemInserted(elderInfoRVAdapter.itemCount)
        }
        if(!data.getStringExtra("name1").equals("name1")) {
            Log.d("name1", data.getStringExtra("name1").toString())
            dataList.apply {
                add(AddInfoRequest(data.getStringExtra("name1"), 1L, data.getStringExtra("contact1")))
            }
            elderInfoRVAdapter.notifyItemInserted(elderInfoRVAdapter.itemCount)
        }
        if(!data.getStringExtra("name2").equals("name2")) {
            dataList.apply {
                add(AddInfoRequest(data.getStringExtra("name2"), 1L, data.getStringExtra("contact2")))
            }
            elderInfoRVAdapter.notifyItemInserted(elderInfoRVAdapter.itemCount)
        }
//        viewBinding.elderName.text= data.getStringExtra("guestName")
//        viewBinding.guardianName1.text = data.getStringExtra("name")
//        viewBinding.guardianNumber1.text = data.getStringExtra("phoneNumber")

        //보호자 layout 클릭하면 수정하기 실행
        elderInfoRVAdapter.setOnBtnClickListener(object : ElderInfoRVAdapter.OnBtnClickListener {
            override fun onBtnClick(view: View, data: AddInfoRequest, position: Int) {
                val intent1 = Intent(this@ElderInfoActivity, GuardianCorrectionActivity::class.java)
                intent1.putExtra("position", position)
                getResultGuardian.launch(intent1)
            }
        })

        //생활패턴 수정하기 버튼
        viewBinding.sleepingTimeBtn.setOnClickListener {
            intent1 = Intent(this, PartternPhachActivity::class.java)
            getResultLivingPT.launch(intent1)
        }

        //온도와 창문열림관리 수정하기 버튼
        viewBinding.degAndWindowBtn.setOnClickListener {
            intent1 = Intent(this, DegAndWindowCorrectionActivity::class.java)
            getResultDAW.launch(intent1)
        }

        //이전 버튼
        viewBinding.backBtn.setOnClickListener {
            intent1 = Intent(this, ElderlyManagerActivity::class.java)
            startActivity(intent1)
        }

        /*  갔다온 Activity에서 intent로 받은 값 처리하기 */

        //보호자 정보 수정하기 Activity에서 얻어온 경우
        getResultGuardian = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            //여기 안에서만 intent로 가져온 값 사용 가능
            //intent 대신 result로 처리하면 돼요
            if (result.resultCode == FIX) { //수정하기 저장 누른 경우
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

                var nameTemp = ""
                var numberTemp = ""
                server.getContacts(0L).enqueue(object : Callback<List<ContactResponse>> {
                    override fun onFailure(call: Call<List<ContactResponse>>, t: Throwable) {
                        Log.e("실패",t.toString())
                    }
                    override fun onResponse(call: Call<List<ContactResponse>>, response: Response<List<ContactResponse>>) {
                        Log.d("성공", response.body().toString())
                        nameTemp = response.body()?.get(0)?.name.toString()
                        Log.d("nameTemp", nameTemp)
                        numberTemp = response.body()?.get(0)?.contact.toString()
                        Log.d("numberTemp", numberTemp)
                    }
                })
                //viewBinding.elderName.text = result.data?.getStringExtra("guestName") <- 어르신 이름 수정 불필요
                val mName = result.data?.getStringExtra("name")
                Log.d("mName", mName.toString())
                val mNumber = result.data?.getStringExtra("phoneNumber")
                val mPosition = result.data?.getIntExtra("position", 0)
                dataList.apply {
                    if (mPosition != null) {
                        //보호자 이름, 전화번호 수정
                        if (mNumber != null) {
                            Log.d("nameTemp", nameTemp)
                            Log.d("numberTemp", numberTemp)
                            set(mPosition, AddInfoRequest(nameTemp,mPosition.toLong(), numberTemp))
                        }
                    }
                }
                if (mPosition != null) {
                    elderInfoRVAdapter.notifyItemChanged(mPosition)
                }
            } else if (result.resultCode == DELETE) { //보호자 삭제 누른 경우
                //삭제일 때 실행 내용
                //sever에서 할 내용 추가 필요
                val mPosition = result.data?.getIntExtra("position", 0)
                if (mPosition != null) {
                    dataList.removeAt(mPosition)
                    elderInfoRVAdapter.notifyItemRemoved(mPosition)
                } else {
                    Log.d("오류발생", "보호자 정부 수정하기에서 데이터 가져오기 실패")
                }
            }
        }

        //생활패턴 수정하기 Activity에서 얻어온 경우
        getResultLivingPT = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == FIX) {
            //수정하기 저장 누른 후
            } else {
                Log.d("오류발생", "생활패턴수정하기에서 데이터 가져오기 실패")
            }
        }

            //온도와 창문열림 관리 수정하기 Activity에서 얻어온 정보
        getResultDAW = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == FIX) {
            //수정하기 저장 누른 후
            } else {
                Log.d("오류발생", "온도와 창문열림 수정하기에서 데이터 가져오기 실패")
            }
        }

    }
        companion object {
        const val DELETE = 1001 //삭제했을 때 resultCode
        const val FIX = 1002    //수정했을 때 resultCode
    }
}