package com.example.oldguard_guardianver.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.oldguard_guardianver.Request.DeletedRecordData
import com.example.oldguard_guardianver.databinding.ItemDeletedDataBinding

class DeletedRecordRVAdapter (private var dataList : ArrayList<DeletedRecordData>) :
    RecyclerView.Adapter<DeletedRecordRVAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(private val viewBinding : ItemDeletedDataBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data : DeletedRecordData, position: Int) {
            viewBinding.deletedElderName.text = data.name
            viewBinding.deletedTime.text = data.time
        }
    }

    //RecycleView 재사용 오류 방지 코드
    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val viewBinding = ItemDeletedDataBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataList[position], position)
    }

    override fun getItemCount(): Int = dataList.size


}