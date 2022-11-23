package com.kimjunseok.chap8hw2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kimjunseok.chap8hw2.databinding.FavoriteDataBinding

class FavoriteMemoAdapter(
    private val favoriteList: ArrayList<Data>): RecyclerView.Adapter<FavoriteMemoAdapter.DataViewHolder>() {

        private lateinit var  binding: FavoriteDataBinding

        inner class DataViewHolder(val binding: FavoriteDataBinding): RecyclerView.ViewHolder(binding.root) {
            fun bind(data: Data) {
                binding.apply {
                    memoTitle.text = data.title
                    memoContent.text = data.content
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        binding = FavoriteDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item = favoriteList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = favoriteList.size
}