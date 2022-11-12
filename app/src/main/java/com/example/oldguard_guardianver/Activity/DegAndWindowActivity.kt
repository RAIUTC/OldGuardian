package com.example.oldguard_guardianver.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.oldguard_guardianver.databinding.ActivityDegAndWindowBinding
import com.example.oldguard_guardianver.intent.MainIntent

class DegAndWindowActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityDegAndWindowBinding

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityDegAndWindowBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //저장 버튼을 눌렀을 때
        viewBinding.nextBtn.setOnClickListener() {
            val intent = Intent(this, MainIntent::class.java)
            startActivity(intent)
        }
    }
}