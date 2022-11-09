package com.kimjunseok.lec3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kimjunseok.lec3.databinding.FragmentFirstBinding

class FirstFragment: Fragment() {
    private lateinit var viewBinding: FragmentFirstBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentFirstBinding.inflate(layoutInflater)
        return viewBinding.root
    }
}