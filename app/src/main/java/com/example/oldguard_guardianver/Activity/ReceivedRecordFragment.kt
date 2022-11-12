package com.example.oldguard_guardianver.Activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.oldguard_guardianver.databinding.FragmentReceivedRecordBinding

class ReceivedRecordFragment : Fragment() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentReceivedRecordBinding.inflate(layoutInflater).root
    }

}