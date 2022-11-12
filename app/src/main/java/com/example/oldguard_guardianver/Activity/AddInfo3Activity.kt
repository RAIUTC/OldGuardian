package com.example.oldguard_guardianver.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.oldguard_guardianver.databinding.ActivityAddInfo3Binding
import com.example.oldguard_guardianver.intent.PatternIntent

class AddInfo3Activity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityAddInfo3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityAddInfo3Binding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val extras = intent.extras

        //다음단계 버튼 눌렀을 때
        viewBinding.nextBtn.setOnClickListener() {
            val intent = Intent(this, PatternIntent::class.java)
            startActivity(intent)
        }
    }
}