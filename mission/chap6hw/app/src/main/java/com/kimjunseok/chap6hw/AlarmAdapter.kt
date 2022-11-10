package com.kimjunseok.chap6hw

import android.graphics.Color
import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView.MultiChoiceModeListener
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.kimjunseok.chap6hw.databinding.ItemDataBinding

class AlarmAdapter(private val datalist: ArrayList<Alarm>) : RecyclerView.Adapter<AlarmAdapter.DataViewHolder>() {

    inner class DataViewHolder(private val viewBinding: ItemDataBinding): RecyclerView.ViewHolder(viewBinding.root){
        fun bind(alarm: Alarm) {
            viewBinding.apply {
                alarmDay.text = alarm.day
                alarmTime.setText("${alarm.hour}:${alarm.minute}")
                alarmChk.isChecked = alarm.isChecked

                alarmChk.setOnClickListener {
                    alarm.isChecked = alarmChk.isChecked
                    if(!alarmChk.isChecked) {
                        alarmDay.setTextColor(Color.GRAY)
                        alarmTime.setTextColor(Color.GRAY)
                    }
                    else {
                        alarmDay.setTextColor(Color.WHITE)
                        alarmTime.setTextColor(Color.WHITE)
                    }
                }
            }
        }

        fun changeMode(editjudge: Boolean) {
            if(editjudge) { // 편집 모드 시
                viewBinding.btnDeletealarm.visibility = View.GONE
                viewBinding.alarmChk.visibility = View.VISIBLE
            }
            else { // show 모드 시
                viewBinding.btnDeletealarm.visibility = View.VISIBLE
                viewBinding.alarmChk.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val viewBinding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(datalist[position])
    }

    override fun getItemCount(): Int = datalist.size

}

