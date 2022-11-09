package com.kimjunseok.lec3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kimjunseok.lec3.databinding.ActivityNewBinding

class NewActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityNewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityNewBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val extras = intent.extras
        // !!를 붙이면 무조건 값이 존재한다는 것을 표시(꼭 넘어올 때만 붙여야함)
        // as String을 붙인 이유는 넣지 않으면 text에 어떤 자료형인지 몰라 들어가지 않음
        val data = extras!!["text"] as String 
        viewBinding.tvGet.text = data
    }
}