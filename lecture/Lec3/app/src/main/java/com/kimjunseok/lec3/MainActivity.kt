package com.kimjunseok.lec3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kimjunseok.lec3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // viewBinding.tvText.text = "안녕"
        viewBinding.btnNext.setOnClickListener {
            val intent = Intent(this, NewActivity::class.java)
            intent.putExtra("text","first")
            startActivity(intent)
        }

        viewBinding.btnNext2.setOnClickListener {
            val intent = Intent(this, FragtestActivity::class.java)
            intent.putExtra("text1","Fragment 1")
            startActivity(intent)
        }
    }
}