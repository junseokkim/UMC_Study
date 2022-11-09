package com.kimjunseok.lec6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kimjunseok.lec6.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceStaet: Bundle?
    ): View? {
        return FragmentSettingBinding.inflate(layoutInflater).root
    }

}