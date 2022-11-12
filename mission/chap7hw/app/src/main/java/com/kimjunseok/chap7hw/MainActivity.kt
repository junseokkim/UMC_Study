package com.kimjunseok.chap7hw

import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.kimjunseok.chap7hw.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var getResultText: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode == RESULT_OK) {
                binding.etTime.setText(null)
                val mPlayer: MediaPlayer = MediaPlayer.create(this, R.raw.sound_alarm)
                mPlayer.start()
                // SoundPool 사용해보기
                val mString = result.data?.getStringExtra("timer_minute")
                val sString = result.data?.getStringExtra("timer_second")
                Toast.makeText(this, "${mString}:${sString} 이 지났습니다.", Toast.LENGTH_LONG).show()
            }
        }

        binding.apply {
            btnNext.setOnClickListener {
                if(etTime.text.isEmpty()) {
                    Toast.makeText(this@MainActivity, "값을 입력하시오",Toast.LENGTH_SHORT).show()
                }
                else {
                    var time = etTime.text.toString().split(":")
                    if(time.size != 2) {
                        Toast.makeText(this@MainActivity, "예시처럼 작성하시오",Toast.LENGTH_SHORT).show()
                        etTime.setText(null)
                    } else {
                        val intent = Intent(this@MainActivity, TimerActivity::class.java)
                        intent.putExtra("timer_minute", time[0])
                        intent.putExtra("timer_second", time[1])
                        getResultText.launch(intent)
                    }
                }
            }
            btnCancel.setOnClickListener {
                etTime.setText(null)
            }
        }
    }
}