package com.example.oldguard_guardianver.intent

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.oldguard_guardianver.Activity.SleepTimeSetActivity
import com.example.oldguard_guardianver.databinding.ActivityLivingPatternBinding

class PatternIntent : AppCompatActivity() {
    private lateinit var viewBinding : ActivityLivingPatternBinding

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityLivingPatternBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.nextBtn.setOnClickListener {
            val intent = Intent(this, SleepTimeSetActivity::class.java)
            startActivity(intent)
        }
    }
}