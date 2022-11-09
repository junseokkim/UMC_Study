package com.kimjunseok.chap6hw

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.kimjunseok.chap6hw.databinding.FragmentAlarmBinding
import com.kimjunseok.chap6hw.databinding.FragmentWorldtimeBinding

class WorldtimeFragment : Fragment() {
    private val viewBinding: FragmentWorldtimeBinding by lazy {
        FragmentWorldtimeBinding.inflate(layoutInflater)
    }

    val wtimeList = arrayListOf<Wtime>()
    val wtimeAdapter = WtimeAdapter(wtimeList)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wtimeList.apply {
            add(Wtime("서울", "오후","7","15","오늘, 0시간"))
            add(Wtime("과달라하라","오전","4","15","오늘, -15시간"))
        }
        viewBinding.wtimeData.adapter = wtimeAdapter
        viewBinding.wtimeData.layoutManager = LinearLayoutManager(this.context)

        // edit Button 클릭 시 숨겨진 deleteButton과 편집 버튼 표시
        viewBinding.btnEdit.setOnClickListener {

        }
        // add Button 클릭 시 Alarm 생성 창 생성
        viewBinding.btnAdd.setOnClickListener {

        }
    }

}