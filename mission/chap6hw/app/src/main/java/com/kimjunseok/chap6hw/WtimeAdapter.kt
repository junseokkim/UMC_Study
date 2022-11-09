package com.kimjunseok.chap6hw

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kimjunseok.chap6hw.databinding.ItemWorldtimeBinding

class WtimeAdapter(private val datalist: ArrayList<Wtime>) : RecyclerView.Adapter<WtimeAdapter.DataViewHolder>() {
    inner class DataViewHolder(private val viewBinding:ItemWorldtimeBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(wtime : Wtime) {
            viewBinding.apply {
                wtimeLocation.text = wtime.location
                wtimeDay.text = wtime.day
                wtimeTime.setText("${wtime.hour}:${wtime.minute}")
                wtimeWhen.text = wtime.whentime
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val viewBinding = ItemWorldtimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(datalist[position])
    }

    override fun getItemCount(): Int = datalist.size
}