package com.example.oldguard_guardianver.Activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.oldguard_guardianver.Adapter.ReceivedRecordRVAdapter
import com.example.oldguard_guardianver.R
import com.example.oldguard_guardianver.Request.ReceivedRecordData
import com.example.oldguard_guardianver.databinding.FragmentReceivedRecordBinding
import com.example.oldguard_guardianver.databinding.ItemReceivedDataBinding
import java.text.FieldPosition

class ReceivedRecordFragment : Fragment() {
    lateinit var viewBinding : FragmentReceivedRecordBinding
    private lateinit var adapter : ReceivedRecordRVAdapter

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentReceivedRecordBinding.inflate(inflater, container, false)

        val itemList = listOf<String>("최신순","오래된순")
        val list = ArrayList<ReceivedRecordData>()
        adapter = ReceivedRecordRVAdapter(list)
        viewBinding.receivedRv.adapter = adapter

        viewBinding.receivedRv.layoutManager = LinearLayoutManager(activity)

        viewBinding.receivedSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (viewBinding.receivedSpinner.getItemAtPosition(position)) {
                    "최신순" -> {
                        updateLatestReceived()
                    }
                    "오래된순" -> {
                        updateOldReceived()
                    }
                    else -> {
                        updateLatestReceived()
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        return FragmentReceivedRecordBinding.inflate(layoutInflater).root
    }

    //최신순으로 add
     fun updateLatestReceived() {
         //예시
         //val list = ArrayList<ReceivedRecordData>()
         //list.add(ReceivedRecordData(add(name,time,type)) 등등
         //adapter.items = list
         //adapter.notifyDataSetChanged()   //필수
     }
    //오래된 순으로 add
    fun updateOldReceived() {
        //위와 동일
    }


}