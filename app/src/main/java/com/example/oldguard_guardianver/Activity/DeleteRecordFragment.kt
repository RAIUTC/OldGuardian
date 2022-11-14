package com.example.oldguard_guardianver.Activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oldguard_guardianver.Adapter.DeletedRecordRVAdapter
import com.example.oldguard_guardianver.Request.DeletedRecordData
import com.example.oldguard_guardianver.databinding.FragmentDeletedRecordBinding
import com.example.oldguard_guardianver.databinding.FragmentReceivedRecordBinding

class DeleteRecordFragment : Fragment() {
    lateinit var viewBinding : FragmentDeletedRecordBinding
    private lateinit var adapter : DeletedRecordRVAdapter

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentDeletedRecordBinding.inflate(inflater, container, false)

        val itemList = listOf<String>("최신순","오래된순")
        val list = ArrayList<DeletedRecordData>()
        adapter = DeletedRecordRVAdapter(list)
        viewBinding.deletedRv.adapter = adapter

        viewBinding.deletedRv.layoutManager = LinearLayoutManager(activity)

        viewBinding.deletedSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (viewBinding.deletedSpinner.getItemAtPosition(position)) {
                    "최신순" -> {
                        updateLatestDeleted()
                    }
                    "오래된순" -> {
                        updateOldDeleted()
                    }
                    else -> {
                        updateLatestDeleted()
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        return FragmentReceivedRecordBinding.inflate(layoutInflater).root
    }

    //최신순으로 add
    fun updateLatestDeleted() {
        //예시
        //val list = ArrayList<DeletedRecordData>()
        //list.add(DeletedRecordData(add(name,time,type)) 등등
        //adapter.items = list
        //adapter.notifyDataSetChanged()   //필수
    }
    //오래된 순으로 add
    fun updateOldDeleted() {
        //위와 동일
    }


}