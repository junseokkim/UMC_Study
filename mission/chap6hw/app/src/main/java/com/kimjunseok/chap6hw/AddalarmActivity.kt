package com.kimjunseok.chap6hw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kimjunseok.chap6hw.databinding.ActivityAddalarmBinding

class AddalarmActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityAddalarmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        viewBinding = ActivityAddalarmBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

    }
}