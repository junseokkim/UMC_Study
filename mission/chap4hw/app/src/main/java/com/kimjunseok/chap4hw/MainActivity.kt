package com.kimjunseok.chap4hw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.kimjunseok.chap4hw.databinding.ActivityMainBinding
var tmp = ""

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnMain.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            val data = viewBinding.etMain.text
            intent.putExtra("content", "$data")
            startActivity(intent)
        }
        viewBinding.btnReset.setOnClickListener {
            viewBinding.etMain.setText("")
        }
    }
    override fun onResume() {
        super.onResume()
        if(tmp != "")   viewBinding.etMain.setText(tmp)
    }

    override fun onStop() {
        super.onStop()
        tmp = viewBinding.etMain.text.toString()
        viewBinding.etMain.setText(tmp)

    }
    override fun onRestart() {
        super.onRestart()
        AlertDialog.Builder(this)
            .setMessage("이어서 작성하시겠습니까?")
            .setPositiveButton("예"){ dialog, which ->
                viewBinding.etMain.setText(tmp)
            }
            .setNegativeButton("아니요"){dialog, which ->
                viewBinding.etMain.setText("")
            }
            .create()
            .show()
        /*
        val builder = AlertDialog.Builder(this)
        builder.setTitle("이어서 작성하시겠습니까?")
            .setPositiveButton("넵",
                DialogInterface.OnClickListener { dialog, id -> viewBinding.etMain.setText(tmp)})
            .setNegativeButton("아니요",
                DialogInterface.OnClickListener { dialog, id -> viewBinding.etMain.setText("")})
        builder.show()
         */
    }
}