package com.example.oldguard_guardianver.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.oldguard_guardianver.Request.AddInfoRequest
import com.example.oldguard_guardianver.Request.DeletedRecordData
import com.example.oldguard_guardianver.databinding.ItemDeletedDataBinding

class DeletedRecordRVAdapter (private var dataList : ArrayList<DeletedRecordData>) : RecyclerView.Adapter<DeletedRecordRVAdapter.ItemViewHolder>() {

    interface OnBtnClickListener {
        fun onBtnClick(view : View, data : DeletedRecordData, position: Int)
    }
    private var btnListener : OnBtnClickListener? = null
    fun setOnBtnClickListener(btnListener: OnBtnClickListener) {
        this.btnListener = btnListener
    }

    inner class ItemViewHolder(private val viewBinding : ItemDeletedDataBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data: DeletedRecordData, position: Int, holder: ItemViewHolder) {
            viewBinding.deletedElderName.text = data.name
            viewBinding.deletedTime.text = data.time

            viewBinding.restoreBtn.setOnClickListener {
                dataList.removeAt(position)
                notifyItemRemoved(position)
                //btnListener?.onBtnClick(holder.itemView, dataList[position], position)
            }
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
        holder.bind(dataList[position], position, holder)
    }

    override fun getItemCount(): Int = dataList.size


}