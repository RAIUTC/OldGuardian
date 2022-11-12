package com.example.oldguard_guardianver.Activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.oldguard_guardianver.Activity.ElderInfoActivity.Companion.FIX
import com.example.oldguard_guardianver.databinding.ActivityElderInfoBinding
import java.time.LocalTime

class ElderInfoActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityElderInfoBinding
    private lateinit var getResultGuardian : ActivityResultLauncher<Intent>
    private lateinit var getResultLivingPT : ActivityResultLauncher<Intent>
    private lateinit var getResultDAW : ActivityResultLauncher<Intent>

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityElderInfoBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        var intent : Intent
        //var data = intent

//        viewBinding.elderName.text= data.getStringExtra("guestName")
//        viewBinding.guardianName1.text = data.getStringExtra("name")
//        viewBinding.guardianNumber1.text = data.getStringExtra("phoneNumber")

        //첫번째 보호자 수정하기 버튼
        viewBinding.guardianBtn1.setOnClickListener {
            intent = Intent(this, GuardianCorrectionActivity::class.java)
            getResultGuardian.launch(intent)
        }

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
            if(result.resultCode == FIX ) { //수정하기 저장 누른 경우
                viewBinding.elderName.text = result.data?.getStringExtra("guestName")
                viewBinding.guardianName1.text = result.data?.getStringExtra("name")
                viewBinding.guardianNumber1.text = result.data?.getStringExtra("phoneNumber")
            }
            else if (result.resultCode == DELETE ) { //보호자 삭제 누른 경우
                //삭제일 때 실행 내용
            }
            else {
                Log.d("오류발생","보호자 정부 수정하기에서 데이터 가져오기 실패")
            }
        }

        //생활패턴 수정하기 Activity에서 얻어온 경우
        getResultLivingPT= registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if(result.resultCode == FIX ) {
                //수정하기 저장 누른 후

            }
            else {
                Log.d("오류발생","생활패턴수정하기에서 데이터 가져오기 실패")
            }
        }

        //온도와 창문열림 관리 수정하기 Activity에서 얻어온 정보
        getResultDAW = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if(result.resultCode == FIX ) {
                //수정하기 저장 누른 후

            }
            else {
                Log.d("오류발생","온도와 창문열림 수정하기에서 데이터 가져오기 실패")
            }
        }


    }

    companion object{
        const val DELETE = 1001 //삭제했을 때 resultCode
        const val FIX = 1002    //수정했을 때 resultCode
    }
}