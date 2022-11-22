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
import com.example.oldguard_guardianver.databinding.FragmentDeletedRecordBinding
import com.example.oldguard_guardianver.databinding.FragmentReceivedRecordBinding
import com.example.oldguard_guardianver.databinding.ItemReceivedDataBinding
import java.text.FieldPosition

class ReceivedRecordFragment : Fragment() {
    lateinit var viewBinding : FragmentReceivedRecordBinding
    private lateinit var adapter : ReceivedRecordRVAdapter
    val manager = LinearLayoutManager(activity)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentReceivedRecordBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemList = listOf<String>("최신순","오래된순")
        val dataList = ArrayList<ReceivedRecordData>()
        adapter = ReceivedRecordRVAdapter(dataList)
        viewBinding.receivedRv.adapter = adapter

//      처음 시작은 역순으로 (최신순) 출력
        manager.reverseLayout = true
        manager.stackFromEnd = true
        viewBinding.receivedRv.layoutManager = manager

        dataList.apply {
            add(ReceivedRecordData("A","2022.11.22 22:15","문자"))
            add(ReceivedRecordData("A","2022.11.22 22:16","전화"))
            add(ReceivedRecordData("C","2022.11.22 22:17","문자"))
            add(ReceivedRecordData("D","2022.11.22 22:18","문자"))
            add(ReceivedRecordData("D","2022.11.22 22:19","문자"))
            add(ReceivedRecordData("A","2022.11.22 22:20","문자"))
            add(ReceivedRecordData("D","2022.11.22 22:21","문자"))
            add(ReceivedRecordData("B","2022.11.22 22:22","문자"))
            add(ReceivedRecordData("A","2022.11.22 22:23","문자"))
            add(ReceivedRecordData("C","2022.11.22 22:24","문자"))
        }
//                adapter.items = dataList
//                adapter.notifyDataSetChanged()   //추가 add시 필수

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
    }

    //최신순으로 add
     fun updateLatestReceived() {
        manager.reverseLayout = true
        manager.stackFromEnd = true
        viewBinding.receivedRv.layoutManager = manager
     }
    //오래된 순으로 add
    fun updateOldReceived() {
        manager.reverseLayout = false
        manager.stackFromEnd = false
        viewBinding.receivedRv.layoutManager = manager
    }
}