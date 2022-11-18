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
import com.example.oldguard_guardianver.Request.AddInfoRequest
import com.example.oldguard_guardianver.databinding.ActivityElderInfoBinding
import java.time.LocalTime

class ElderInfoActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityElderInfoBinding
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
        var intent: Intent

        viewBinding.rvElderInfo.adapter = elderInfoRVAdapter

        val manager = LinearLayoutManager(this)
        viewBinding.rvElderInfo.layoutManager = manager

        //test용 추가. 데이터 관리할 때 변경 필요
        dataList.apply {
            add(AddInfoRequest("김복원", "01077437539"))
            add(AddInfoRequest("김복투", "01077437539"))
            add(AddInfoRequest("김복쓰리", "01077437539"))
        }
//        viewBinding.elderName.text= data.getStringExtra("guestName")
//        viewBinding.guardianName1.text = data.getStringExtra("name")
//        viewBinding.guardianNumber1.text = data.getStringExtra("phoneNumber")

        //보호자 layout 클릭하면 수정하기 실행
        elderInfoRVAdapter.setOnItemClickListener(object : ElderInfoRVAdapter.OnItemClickListener {
            override fun onItemClick(view: View, data: AddInfoRequest, position: Int) {
                val intent = Intent(this@ElderInfoActivity, GuardianCorrectionActivity::class.java)
                intent.putExtra("position", position)
                getResultGuardian.launch(intent)
            }
        })

        //생활패턴 수정하기 버튼
        viewBinding.sleepingTimeBtn.setOnClickListener {
            intent = Intent(this, PartternPhachActivity::class.java)
            getResultLivingPT.launch(intent)
        }

        //온도와 창문열림관리 수정하기 버튼
        viewBinding.degAndWindowBtn.setOnClickListener {
            intent = Intent(this, DegAndWindowCorrectionActivity::class.java)
            getResultDAW.launch(intent)
        }

        //이전 버튼
        viewBinding.backBtn.setOnClickListener {
            intent = Intent(this, ElderlyManagerActivity::class.java)
            startActivity(intent)
        }

        /*  갔다온 Activity에서 intent로 받은 값 처리하기 */

        //보호자 정보 수정하기 Activity에서 얻어온 경우
        getResultGuardian = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            //여기 안에서만 intent로 가져온 값 사용 가능
            //intent 대신 result로 처리하면 돼요
            if (result.resultCode == FIX) { //수정하기 저장 누른 경우
                //viewBinding.elderName.text = result.data?.getStringExtra("guestName") <- 어르신 이름 수정 불필요
                val mName = result.data?.getStringExtra("name")
                val mNumber = result.data?.getStringExtra("phoneNumber")
                val mPosition = result.data?.getIntExtra("position", 0)
                dataList.apply {
                    if (mPosition != null) {
                        //보호자 이름, 전화번호 수정
                        set(mPosition, AddInfoRequest(mName.toString(), mNumber.toString()))
                        elderInfoRVAdapter.notifyItemChanged(mPosition)
                    }
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