package com.kimjunseok.lec6_2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainVPAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    // 총 iTem의 개수
    override fun getItemCount(): Int = 2
    // position에 따라서 어떤 Fragment를 보여줄 지
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> OneFragment()
            1 -> TwoFragment()
            else -> OneFragment() // 해당 함수에서는 개수를 알지 못하기 때문에 예외사항을 적어줘야 한다
        }
    }
}