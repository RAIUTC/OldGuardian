package com.example.oldguard_guardianver.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.oldguard_guardianver.databinding.ActivityAddInfo2Binding
import com.example.oldguard_guardianver.intent.PatternIntent

/**   두번째 어르신 추가 화면   */
class AddInfo2Activity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityAddInfo2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //activity_add_info2 와 연결
        viewBinding = ActivityAddInfo2Binding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val extras = intent.extras

        //연락처 추가하기 버튼 눌렀을 때
        viewBinding.addNumberBtn.setOnClickListener {
            val intent = Intent(this, AddInfo3Activity::class.java)
            startActivity(intent)
        }

        //다음단계 버튼 눌렀을 때
        viewBinding.nextBtn.setOnClickListener() {
            val intent = Intent(this, PatternIntent::class.java)
            startActivity(intent)
        }
    }
}