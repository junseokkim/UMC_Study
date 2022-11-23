package com.kimjunseok.chap6hw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kimjunseok.chap6hw.databinding.ActivityAddalarmBinding

class AddalarmActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityAddalarmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        viewBinding = ActivityAddalarmBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        val mIntent = Intent(this, AlarmFragment::class.java) //
        viewBinding.btnCancel.setOnClickListener{
            setResult(RESULT_OK, mIntent)
            if(!isFinishing) finish()
        }
    }
}