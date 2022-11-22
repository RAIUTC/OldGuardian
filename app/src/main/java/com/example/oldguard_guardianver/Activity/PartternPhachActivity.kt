package com.example.oldguard_guardianver.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.oldguard_guardianver.databinding.ActivityPatternPhachBinding

/**   어르신 관리에서 생활패턴 수정 화면   */
class PartternPhachActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityPatternPhachBinding   //activity_patten_phach 화면과 연결

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityPatternPhachBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //저장 버튼을 눌렀을 때
        viewBinding.saveBtn.setOnClickListener {
            var startHour = viewBinding.elderStartHour.toString()
            var startMin = viewBinding.elderStartMinute.toString()
            var endHour = viewBinding.elderEndHour.toString()
            var endMin = viewBinding.elderEndMinute.toString()
            var messageTime = viewBinding.elderMessageTime.toString()
            var callTime = viewBinding.elderCallTime.toString()
            var sosTime = viewBinding.elderSosTime.toString()

            //레트로핏 사용하는 코드 써주세요

            val intent = Intent(this, ElderInfoActivity::class.java)
            setResult(FIX, intent)
            finish()
        }
    }

    companion object{
        const val FIX = 1002    //수정했을 때 resultCode
    }
}