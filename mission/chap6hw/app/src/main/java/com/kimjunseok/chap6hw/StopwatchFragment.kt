package com.kimjunseok.chap6hw

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.kimjunseok.chap6hw.databinding.FragmentStopwatchBinding

class StopwatchFragment : Fragment() {
    private val viewBinding: FragmentStopwatchBinding by lazy {
        FragmentStopwatchBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val stopwatchVPAdapter = StopwatchVPAdapter(this.requireActivity())
        viewBinding.vpStopwatch.adapter = stopwatchVPAdapter

        val tapTitleArray = arrayOf(
            "1",
            "2",
            "3",
        )
        TabLayoutMediator(viewBinding.tapStopwatch, viewBinding.vpStopwatch) {tap,position->
            tap.text =tapTitleArray[position]
        }.attach()
    }
}
