package com.kimjunseok.chap8hw2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.kimjunseok.chap8hw2.databinding.ActivityWriteBinding

class WriteActivity : AppCompatActivity() {
    var TitleTmp = ""    // 화면 이탈 시 제목 데이터 저장할 전역변수 설정
    var ContentTmp = ""  // 화면 이탈 시 내용 데이터 저장할 전역변수 설정

    private lateinit var binding: ActivityWriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // intent를 통한 extras값 받기
        val extras = intent.extras
        val chk = extras!!["chk"] as Boolean    // 추가 or 편집 모드 판단 변수 설정(true : 편집, false : 추가)
        var idx = -1    // position값 초기화 (추가 모드 시 idx값 -1)
        var key = -1    // key값 초기화(추가 모드 시 key값 -1)
        var favorite = extras!!["favorite"] as Boolean
        if(chk) {
            // 편집 모드 시 기존 데이터들 반환 및 값 입력
            val oldTitle = extras!!["title"] as String
            val oldContent = extras!!["content"] as String
            binding.etTitle.setText(oldTitle)
            binding.etContent.setText(oldContent)
            idx = extras!!["idx"] as Int    // position값 반환
            key = extras!!["key"] as Int
        }

        binding.backBtn.setOnClickListener {
            val title = binding.etTitle.text
            val content = binding.etContent.text
            val mIntent = Intent(this, MainActivity::class.java)
            AlertDialog.Builder(this)
                .setMessage("저장하시겠습니까?")
                .setPositiveButton("네"){dialog, which ->
                    mIntent.putExtra("memo_title",title.toString())
                    mIntent.putExtra("memo_content",content.toString())
                    mIntent.putExtra("idx",idx)     // idx값 넘겨주기( idx = -1 일 경우 새로운 메모 추가를 나타냄)
                    mIntent.putExtra("key",key)     // key값 넘겨주기( key = -1 일 경우 새로운 메모 추가를 나타냄)
                    mIntent.putExtra("favorite", favorite)   // 즐겨찾기 여부 넘겨주기
                    if(!isFinishing) finish()
                }
                .setNegativeButton("아니오"){ dialog, which->
                    if(!isFinishing) finish()
                }
                .show()
                .create()
            setResult(RESULT_OK, mIntent)
        }

        binding.saveBtn.setOnClickListener {
            val title = binding.etTitle.text
            val content = binding.etContent.text
            val mIntent = Intent(this, MainActivity::class.java).apply {
                putExtra("memo_title",title.toString())
                putExtra("memo_content",content.toString())
                putExtra("idx", idx)    // idx값 넘겨주기( idx = -1 일 경우 새로운 메모 추가를 나타냄)
                putExtra("key",key)     // key값 넘겨주기( key = -1 일 경우 새로운 메모 추가를 나타냄)
                putExtra("favorite", favorite)   // 즐겨찾기 여부 넘겨주기
            }
            setResult(RESULT_OK, mIntent)
            if(!isFinishing) finish()
        }
    }

    // 메모를 입력하는 EditText의 현재 값을 Activity에 전역변수를 하나 만들어서 담아두기
    // 전역변수에 저장하고 나서, 메모를 입력하는 EditText는 비우기
    override fun onStop() {
        super.onStop()
        TitleTmp = binding.etTitle.text.toString()
        ContentTmp = binding.etContent.text.toString()
        binding.etTitle.setText("")
        binding.etContent.setText("")
    }

    // 예 클릭 시, onStop에서 저장한 전역변수 내용으로 EditText 내용으로 설정하기
    // 아니요 클릭 시, onStop에서 저장했던 변수 비우기
    override fun onRestart() {
        super.onRestart()
        AlertDialog.Builder(this)
            .setMessage("이어서 작성하시겠습니까?")
            .setPositiveButton("예"){ dialog, which ->
                binding.etTitle.setText(TitleTmp)
                binding.etContent.setText(ContentTmp)
            }
            .setNegativeButton("아니요"){dialog, which ->
                TitleTmp = ""
                ContentTmp = ""
            }
            .create()
            .show()
    }
}