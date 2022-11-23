package com.kimjunseok.chap7hw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.kimjunseok.chap7hw.databinding.ActivityTimerBinding

class TimerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTimerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val extras = intent.extras
        var mString = extras!!["timer_minute"] as String
        var sString = extras!!["timer_second"] as String
        val intent = Intent(this@TimerActivity, MainActivity::class.java)
        intent.putExtra("timer_minute",mString)
        intent.putExtra("timer_second",sString)
        var handler = Handler(mainLooper)

        var minute = mString.toInt()
        var second = sString.toInt()

        binding.apply {
            timer.setText(String.format("%02d:%02d",minute, second))
            btnStart.setOnClickListener {
                Thread() {
                    while(minute != 0 || second != 0) {
                        if(second == 0) {
                            minute--
                            second = 59
                        }
                        else second--
                        handler.post {
                            timer.setText(String.format("%02d:%02d",minute, second))
                        }
                        Thread.sleep(1000)
                    }
                    setResult(RESULT_OK, intent)
                    if(!isFinishing) finish()
                }.start()
            }

            // 정지 버튼 구현하기
            btnStop.setOnClickListener {

            }
        }

    }
}