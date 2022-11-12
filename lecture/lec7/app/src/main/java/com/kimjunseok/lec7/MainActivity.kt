package com.kimjunseok.lec7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.kimjunseok.lec7.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        Log.d("Lifecycle", "onCreate")

        val imageList: ArrayList<Int> = arrayListOf(
            R.drawable.threadtest_1,
            R.drawable.threadtest_2,
            R.drawable.threadtest_3,
            R.drawable.threadtest_4,
            R.drawable.threadtest_5
        )
        var imgIdx = 0

        var handler = Handler(Looper.getMainLooper())

        Thread() {
            while (true) {
                if (imgIdx == imageList.size - 1) {
                    imgIdx = 0
                } else {
                    imgIdx++
                }
                // handler.post -> 여기서 하기 힘드니까 Handler가 있는 쓰레드 (UI Thread)에서 해줘~
                handler.post {
                    viewBinding.ivImage.setImageResource(imageList[imgIdx])
                }
                Thread.sleep(2000)
            }
        }.start()
    }
}