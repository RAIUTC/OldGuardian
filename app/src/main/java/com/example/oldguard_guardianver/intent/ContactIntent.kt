package com.example.oldguard_guardianver.intent

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.oldguard_guardianver.Activity.AddInfoActivity
import com.example.oldguard_guardianver.databinding.ActivityContactInfoBinding

class ContactIntent : AppCompatActivity() {
    private lateinit var viewBinding: ActivityContactInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityContactInfoBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.nextBtn.setOnClickListener {
            val intent = Intent( this, AddInfoActivity::class.java)
            startActivity((intent))
        }

    }

}