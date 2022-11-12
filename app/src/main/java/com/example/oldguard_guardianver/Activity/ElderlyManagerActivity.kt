package com.example.oldguard_guardianver.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.oldguard_guardianver.databinding.ActivityElderlyManagerBinding

class ElderlyManagerActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityElderlyManagerBinding
    private lateinit var getResultText : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityElderlyManagerBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //데이터가 없을 때 layout_elder_info 안보이게 할 예정
        //https://kdsoft-zeros.tistory.com/m/102 참고


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
        ) { result ->
            //기록 activity를 읽기만 했을 때
            if(result.resultCode == READ) {
                //화면만 돌아옴 따로 추가할 내용 X
            }
            //기록 activity를 읽고 삭제된 목록을 복구했을 떄
            else if (result.resultCode == WRITE) {
                //RecyclerView add 사용해서 추가
            }
            else {
                Log.d("에러메시지","resultCode값 없음")
            }
        }
    }
    companion object{
        const val READ = 1001
        const val WRITE = 1002
    }
}