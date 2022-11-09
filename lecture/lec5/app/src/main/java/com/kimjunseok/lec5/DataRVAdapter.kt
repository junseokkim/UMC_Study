package com.kimjunseok.lec5

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kimjunseok.lec5.databinding.ItemDataBinding

class DataRVAdapter(private val dataList: ArrayList<Data>): RecyclerView.Adapter<DataRVAdapter.DataViewHolder>() {
    // private val dataList: ArrayList<Data> = arrayListOf() --> 이렇게 데이터리스트를 선언하거나 인자값으로 선언해도 됨
    // 여기서 선언하는 경우 Activity에서 접근할 수 있도록 따로 add함수 등을 만들어줘야함
    // 방법 1
    // private val checkBoxStatus = SparseBooleanArray()
    //viewHolder 객체
    inner class DataViewHolder(private val viewBinding: ItemDataBinding): RecyclerView.ViewHolder(viewBinding.root){
        fun bind(data: Data) {
            viewBinding.apply {
                tvTitle.text = data.title
                tvDesc.text = data.desc
                // 방법 1
                /*
                swtData.isChecked = checkBoxStatus[adapterPosition]
                swtData.setOnClickListener {
                    if (!swtData.isChecked)
                        checkBoxStatus.put(adapterPosition, false)
                    else
                        checkBoxStatus.put(adapterPosition, true)
                    notifyItemChanged(adapterPosition)
                }
                              */
                // 방법 2
                swtData.isChecked = data.isChecked

                swtData.setOnClickListener {
                    data.isChecked = swtData.isChecked
                    notifyItemChanged(adapterPosition)
                }

            }
        }
    }

    //ViewHolder가 만들어질 떼 실행할 동작
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val viewBinding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false) //false로 고정
        return DataViewHolder(viewBinding)
    }

    //viewHolder가 실제로 데이터를 표시해야 할 때 호출되는 함수
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        // holder.bind(dataList[position])
        // 방법 2 :
        holder.bind(dataList[position])
    }

    // 표현할 Item 총 개수
    override fun getItemCount(): Int = dataList.size
}