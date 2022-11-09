package com.kimjunseok.lec6_2

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kimjunseok.lec6_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        // viewPager2 연동
        val mainVPAdapter = MainVPAdapter(this)
        viewBinding.vpMain.adapter = mainVPAdapter

        val tabTitleArray = arrayOf(
            "One",
            "Two",
        )
        TabLayoutMediator(viewBinding.tapMain, viewBinding.vpMain) {tab, position ->
            tab.text = tabTitleArray[position]
        }.attach() // 꼭 붙여야함
    }
}