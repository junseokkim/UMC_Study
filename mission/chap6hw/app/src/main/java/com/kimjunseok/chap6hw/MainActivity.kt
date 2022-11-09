package com.kimjunseok.chap6hw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kimjunseok.chap6hw.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        // WorldtimeFragment를 기본으로 설정
        supportFragmentManager
            .beginTransaction()
            .replace(viewBinding.containerFragment.id, WorldtimeFragment())
            .commitAllowingStateLoss()

        viewBinding.navBottom.run {
            setOnItemSelectedListener {
                when(it.itemId) {
                    R.id.menu_worldtime -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.containerFragment.id, WorldtimeFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_alarm -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.containerFragment.id, AlarmFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_stopwatch -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.containerFragment.id, StopwatchFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_timer -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.containerFragment.id, TimerFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }
            // 처음 실행했을 때 메뉴 홈에 해당하는 id를 가리키게 됨
            selectedItemId = R.id.menu_worldtime
        }
    }
}