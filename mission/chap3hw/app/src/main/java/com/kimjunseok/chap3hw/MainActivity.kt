package com.kimjunseok.chap3hw

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.kimjunseok.chap3hw.databinding.ActivityMainBinding

class MainActivity() : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    // private lateinit var getResultText: ActivityResultLauncher<Intent>
    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()){

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
   /*     getResultText =registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode == RESULT_OK) {
                val mString = result.data?.getStringExtra("BACK")?:""
                val myToast = Toast.makeText(this,"$mString", Toast.LENGTH_LONG).show()
            }
        }
    */

        viewBinding.btnNext.setOnClickListener {
            val intent = Intent(this, NextActivity::class.java)
            val data = viewBinding.etGetText.text
            intent.putExtra("text", "$data")
            startActivity(intent)
        }
    }

}