package com.kimjunseok.lec6_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kimjunseok.lec6_2.databinding.FragmentOneBinding

class OneFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceStaet: Bundle?
    ): View? {
        return FragmentOneBinding.inflate(layoutInflater).root
    }
}