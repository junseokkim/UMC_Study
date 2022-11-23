package com.kimjunseok.chap6hw

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kimjunseok.chap6hw.databinding.ActivityMainBinding
import com.kimjunseok.chap6hw.databinding.FragmentAlarmBinding

class AlarmFragment : Fragment() {
    private val viewBinding: FragmentAlarmBinding by lazy {
        FragmentAlarmBinding.inflate(layoutInflater)
    }
    private lateinit var getResultText: ActivityResultLauncher<Intent>
    // 정렬 방법 설정 하기
    val alarmList = arrayListOf<Alarm>()
    val alarmAdpater =AlarmAdapter(alarmList)
    var judgeEdit: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        alarmList.apply {
            add(Alarm("오전", "9", "30"))
            add(Alarm("오후", "1", "00"))
        }
        viewBinding.alarmData.adapter = alarmAdpater
        viewBinding.alarmData.layoutManager = LinearLayoutManager(this.context)

        getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                val dString = result.data?.getStringExtra("alarm_day") as String
                val hString = result.data?.getStringExtra("alarm_hour") as String
                val mString = result.data?.getStringExtra("alarm_minute") as String
                alarmList.add(Alarm(dString, hString, mString, true))
                alarmAdpater.notifyItemInserted(alarmAdpater.itemCount)
            }
        }
        // edit Button 클릭 시 숨겨진 deleteButton과 편집 버튼 표시
        viewBinding.btnEdit.setOnClickListener {
            changeMode(viewBinding)
            alarmAdpater
        }

        // add Button 클릭 시 Alarm 생성 창 생성
        viewBinding.btnAdd.setOnClickListener {
            val mIntent = Intent(requireContext(), AddalarmActivity::class.java)
            getResultText.launch(mIntent)
        }
    }

    fun changeMode(viewBinding: FragmentAlarmBinding) {
        if(judgeEdit) { // edit모드 시
            viewBinding.btnEdit.setText("편집")
            judgeEdit = false
        }
        else {
            viewBinding.btnEdit.setText("완료")
            judgeEdit = true
        }
    }
}