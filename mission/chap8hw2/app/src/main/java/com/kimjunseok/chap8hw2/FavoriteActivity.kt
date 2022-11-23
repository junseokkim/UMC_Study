package com.kimjunseok.chap8hw2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.kimjunseok.chap8hw2.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val extras = intent.extras

        val favoriteList: ArrayList<Data> = extras!!["favoriteList"] as ArrayList<Data>
        val favoriteAdapter = FavoriteMemoAdapter(favoriteList)

        binding.fmemoData.adapter = favoriteAdapter
        binding.fmemoData.layoutManager = LinearLayoutManager(this)

        binding.btnBack.setOnClickListener {
            val mIntent = Intent(this@FavoriteActivity, MainActivity::class.java)
            if(!isFinishing) finish()
            startActivity(mIntent)
        }
    }
}