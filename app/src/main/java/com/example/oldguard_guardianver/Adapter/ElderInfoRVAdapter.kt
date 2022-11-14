package com.example.oldguard_guardianver.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.oldguard_guardianver.Request.AddInfoRequest
import com.example.oldguard_guardianver.databinding.ItemElderInfoBinding

class ElderInfoRVAdapter (private val datalist : ArrayList<AddInfoRequest>) : RecyclerView.Adapter<ElderInfoRVAdapter.DataViewHolder>() {
    interface  OnItemClickListener {
        fun onItemClick(view : View, data : AddInfoRequest, position : Int)
    }
    private var listener : OnItemClickListener? = null
    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }
    inner class DataViewHolder(private val viewBinding : ItemElderInfoBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        private var pos : Int = 0
        fun bind(data : AddInfoRequest, position : Int) {
            viewBinding.guardianName1.text = data.name
            viewBinding.guardianNumber1.text = data.number
        }
    }

    //RecycleView 재사용 오류 방지 코드
    override fun getItemViewType(position: Int): Int {
        return position
    }
    //ViewBinder 만들어질 때 실행할 동작
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val viewBinding = ItemElderInfoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DataViewHolder(viewBinding)
    }

    //ViewHolder가 실제로 데이터를 표시해야 할 때 호출되는 함수
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(datalist[position], position)
        holder.itemView.setOnClickListener {
            datalist.removeAt(position)
            notifyItemRemoved(position)
        }

    }

    override fun getItemCount(): Int = datalist.size

}