package com.kimjunseok.lec5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.LinearLayoutManager
import com.kimjunseok.lec5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val dataList = ArrayList<Data>()
        dataList.apply{
            for(i in 1 .. 30)
            {
                add(Data("hello","$i",i))
            }
        }
        val dataRVAdapter = DataRVAdapter(dataList)

        viewBinding.rvData.adapter = dataRVAdapter
        viewBinding.rvData.layoutManager = LinearLayoutManager(this)

        /*Handler(mainLooper).postDelayed({  // 단순히 데이터를 업데이트만 하면 안되고 업데이트되었다고 adapter한테 알려주어야함
            dataList.apply{
                add(Data("hello","31"))
                add(Data("hello","32"))
                add(Data("hello","33"))
                add(Data("hello","34"))
                add(Data("hello","35"))
            }
            // 업데이트된 것을 알려주는 코드들
            dataRVAdapter.notifyItemRangeInserted(31,5)
            // dataRVAdapter.notifyDataSetChanged() --> item 전체를 새로 가져오게 되는 경우
        },1000)

         */


    }
}