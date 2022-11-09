package com.kimjunseok.chap6hw

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class StopwatchVPAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> Stopwatch1Fragment()
            1 -> Stopwatch2Fragment()
            2 -> Stopwatch3Fragment()
            else -> Stopwatch1Fragment()
        }
    }
}