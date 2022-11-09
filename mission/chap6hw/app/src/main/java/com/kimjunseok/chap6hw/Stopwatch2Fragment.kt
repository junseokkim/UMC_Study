package com.kimjunseok.chap6hw

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kimjunseok.chap6hw.databinding.FragmentStopwatch2Binding

class Stopwatch2Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentStopwatch2Binding.inflate(layoutInflater).root
    }
}