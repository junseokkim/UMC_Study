package com.kimjunseok.chap3hw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kimjunseok.chap3hw.databinding.ActivityNextBinding

class NextActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityNextBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityNextBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val extras = intent.extras
        val data = extras!!["text"] as String

        viewBinding.textView.text = data

        viewBinding.btnNext2.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
        viewBinding.btnBack.setOnClickListener {
            val mIntent = Intent(this, MainActivity::class.java).apply {
                putExtra("BACK","Back" )
            }
            setResult(RESULT_OK, mIntent)
            if(!isFinishing) finish()
        }
    }
}