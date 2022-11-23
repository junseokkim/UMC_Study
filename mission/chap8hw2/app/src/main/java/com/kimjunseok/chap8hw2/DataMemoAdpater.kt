package com.kimjunseok.chap8hw2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kimjunseok.chap8hw2.databinding.ItemDataBinding

class DataMemoAdapter(
    private val dataList: ArrayList<Data>): RecyclerView.Adapter<DataMemoAdapter.DataViewHolder>() {

    private lateinit var binding: ItemDataBinding

    // 클릭 인터페이스를 정의
    interface MyItemClickListener {
        fun onItemClick(position: Int)
        fun onLongClick(position: Int)        // 길게 눌렀을 경우에 대한 함수
        fun onEditClick(position: Int)        // 편집버튼 클릭에 대한 함수
        fun onDeleteClick(position: Int)      // 삭제버튼 클릭에 대한 함수
        fun onEmptyStarClick(position: Int)   // 즐겨찾기 버튼 클릭에 대한 함수1
        fun onFullStarClick(position: Int)    // 즐겨찾기 버튼 클릭에 대한 함수2
    }
    //클릭 리스너 설정
    private lateinit var mItemClickListener: MyItemClickListener

    //클릭 리스너 등록 메서드(메인 액티비티에서 람다식 혹은 inner 클래스로 호출)
    fun setMyItemClickListner(itemClickListener: MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    inner class DataViewHolder(val binding: ItemDataBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(data: Data) {
            binding.apply {
                memoTitle.text = data.title
                memoContent.text = data.content
                if(data.favorite) {
                    memoFullstar.visibility = View.VISIBLE
                    memoEmptystar.visibility = View.INVISIBLE
                } else {
                    memoFullstar.visibility = View.INVISIBLE
                    memoEmptystar.visibility = View.VISIBLE
                }
            }
            binding.root.setOnClickListener {
                mItemClickListener.onItemClick(adapterPosition)
                return@setOnClickListener
            }
            binding.root.setOnLongClickListener {
                // 길게 클릭시 수정/삭제 버튼 visible 설정
                mItemClickListener.onLongClick(adapterPosition)
                binding.apply {
                    if(memoEdit.visibility == View.VISIBLE){
                        memoEdit.visibility = View.INVISIBLE
                        memoDelete.visibility = View.INVISIBLE
                    }
                    else {
                        memoEdit.visibility = View.VISIBLE
                        memoDelete.visibility = View.VISIBLE
                    }
                }
                return@setOnLongClickListener(true)
            }
            binding.memoEdit.setOnClickListener {
                // 편집 버튼 클릭 시 수정/삭제 버튼 invisible 설정
                mItemClickListener.onEditClick(adapterPosition)
                binding.apply {
                    memoEdit.visibility = View.INVISIBLE
                    memoDelete.visibility = View.INVISIBLE
                }
                return@setOnClickListener
            }
            binding.memoDelete.setOnClickListener {
                mItemClickListener.onDeleteClick(adapterPosition)
                binding.apply {
                    memoEdit.visibility = View.INVISIBLE
                    memoDelete.visibility = View.INVISIBLE
                    memoFullstar.visibility = View.INVISIBLE
                    memoEmptystar.visibility = View.VISIBLE
                }
                return@setOnClickListener
            }
            binding.memoEmptystar.setOnClickListener {
                mItemClickListener.onEmptyStarClick(adapterPosition)
                binding.apply {
                    memoEmptystar.visibility = View.INVISIBLE
                    memoFullstar.visibility = View.VISIBLE
                }
                return@setOnClickListener
            }
            binding.memoFullstar.setOnClickListener {
                mItemClickListener.onFullStarClick(adapterPosition)
                binding.apply {
                    memoEmptystar.visibility = View.VISIBLE
                    memoFullstar.visibility = View.INVISIBLE
                }
                return@setOnClickListener
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        binding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = dataList.size
}