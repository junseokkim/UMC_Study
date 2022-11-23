package com.kimjunseok.chap8hw2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.kimjunseok.chap8hw2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var getResultText: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataList: ArrayList<Data> = arrayListOf()
        val dataMemoAdapter = DataMemoAdapter(dataList)

        val roomDB = AppDatabase.getInstance(this)
        // sharePreference 객체 및 editor 생성
        val sharedPrefs = getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()

        if(roomDB != null) {
            val memoList = roomDB.memoDao().selectAll()
            // 반복문을 이용하여 이전에 저장된 값들 저장
            for(i: Int in 0 until memoList.size)
            {
                // sharedPreference를 통해 각 메모별 즐겨찾기 여부 받아오기
                val getStar = sharedPrefs.getBoolean("${memoList[i].title}", false)
                dataList.add(Data(memoList[i].title, memoList[i].content, getStar))
            }
            // 변경사항 어댑터에 알려주기
            dataMemoAdapter.notifyItemInserted(dataMemoAdapter.itemCount)
        }

        // WriteActivity에서 돌아왔을 때 실행
        getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode ==RESULT_OK) {
                val tString = result.data?.getStringExtra("memo_title")
                val cString = result.data?.getStringExtra("memo_content")
                // position값 WriteActivity에서 받아오기 (새로 만든 경우 -1을 반환)
                val idx = result.data?.getIntExtra("idx",-1) as Int
                val key = result.data?.getIntExtra("key",-1) as Int
                // 저장한 경우 실행 (둘 다 null이 아닌 경우)
                if(tString != null && cString != null)
                {
                    // 새로운 메모를 만든 경우
                    if(idx == -1) {
                        dataList.add(Data(tString.toString(), cString.toString(), false))
                        dataMemoAdapter.notifyItemInserted(dataMemoAdapter.itemCount)
                        binding.memoCnt.text = "${dataMemoAdapter.itemCount}개의 메모"
                        // 데이터 리스트에 추가한 후 데이터베이스에 메모 정보 추가
                        if (roomDB != null) {
                            val newmemo = Memo(tString.toString(),cString.toString())
                            roomDB.memoDao().insert(newmemo)
                        }
                        // 새로운 메모의 최초 즐겨찾기 상태는 false로 둔다.
                        editor.putBoolean("${tString}", false).apply()
                    }
                    // 기존 메모를 수정한 경우
                    else {
                        val favorite = result.data?.getBooleanExtra("favorite",false)
                        val oldTitle = dataList[idx].title
                        dataList.set(idx, Data(tString,cString, favorite as Boolean))
                        dataMemoAdapter.notifyDataSetChanged()
                        // key값을 통하여 title과 content내용 수정
                        if (roomDB != null) {
                            roomDB.memoDao().updateTitleByMemoId(key, tString)
                            roomDB.memoDao().updateContentByMemoId(key, cString)
                        }
                        // 기존 제목을 가진 data 삭제한 후 새로운 제목을 가진 data를 추가
                        editor.remove("${oldTitle}")
                        editor.putBoolean("${tString}", favorite)
                        editor.apply()
                    }
                }
            }
        }

        // dataMemoAdapter를 통한 클릭 리스너
        dataMemoAdapter.setMyItemClickListner(object : DataMemoAdapter.MyItemClickListener {
            // ItemView 클릭 시 삭제 함수
            override fun onItemClick(position: Int) {
                if (roomDB != null) {
                    val memo = roomDB.memoDao().selectByMemoTitle(dataList[position].title)
                    roomDB.memoDao().delete(memo)
                }
                // 메모 title을 통해 메모 삭제
                editor.remove("${dataList[position].title}").apply() // TroubleShooting
                // 메모 title을 통해 메모 삭제
                dataList.removeAt(position)
                dataMemoAdapter.notifyItemRemoved(position)
                binding.memoCnt.text = "${dataMemoAdapter.itemCount}개의 메모"
            }

            // ItemView 길게 클릭 시 함수
            override fun onLongClick(position: Int) {

            }
            // ItemView 내 편집버튼 클릭 시 함수
            override fun onEditClick(position: Int) {
                val eIntent = Intent(this@MainActivity, WriteActivity::class.java)
                var key = -1
                Log.d("test","${dataList[position].title}")
                if (roomDB != null) {
                    key = roomDB.memoDao().getIdByMemoTitle(dataList[position].title)
                }
                eIntent.putExtra("title", dataList[position].title)
                eIntent.putExtra("content",dataList[position].content)
                // 편집과 추가에 대한 구분을 위한 bool값 추가
                eIntent.putExtra("chk",true)
                // 편집하려는 해당 itemView의 position 추가
                eIntent.putExtra("idx",position)
                // 편집하려는 appdatabase의 key 추가
                eIntent.putExtra("key", key)
                eIntent.putExtra("favorite", dataList[position].favorite)
                getResultText.launch(eIntent)
            }
            // ItemView 내 삭제버튼 클릭 시 함수
            override fun onDeleteClick(position: Int) {
                // 메모 title을 통해 메모 삭제
                if (roomDB != null) {
                    val memo = roomDB.memoDao().selectByMemoTitle(dataList[position].title)
                    roomDB.memoDao().delete(memo)
                }
                editor.remove("${dataList[position].title}")
                // 메모 title을 통해 메모 삭제
                editor.apply()
                dataList.removeAt(position)
                dataMemoAdapter.notifyItemRemoved(position)
                binding.memoCnt.text = "${dataMemoAdapter.itemCount}개의 메모"
            }

            override fun onEmptyStarClick(position: Int) {
                var updateMemo = dataList[position]
                updateMemo.favorite = !updateMemo.favorite
                dataList.set(position, updateMemo)
                dataMemoAdapter.notifyDataSetChanged()
                editor.putBoolean("${dataList[position].title}", true).apply()
            }

            override fun onFullStarClick(position: Int) {
                var updateMemo = dataList[position]
                updateMemo.favorite = !updateMemo.favorite
                dataList.set(position, updateMemo)
                dataMemoAdapter.notifyDataSetChanged()
                editor.putBoolean("${dataList[position].title}", false).apply()
            }

        })

        binding.memoData.adapter = dataMemoAdapter
        binding.memoData.layoutManager = LinearLayoutManager(this)
        // binding.memoData.setHasFixedSize(true) //

        // btnAdd 클릭시 writeActivity로 화면 전환
        binding.btnAdd.setOnClickListener {
            val mIntent = Intent(this@MainActivity, WriteActivity::class.java)
            // 편집과 추가에 대한 구분을 위한 bool값 추가
            mIntent.putExtra("chk",false)
            // 즐겨찾기 상태 보내기
            mIntent.putExtra("favorite",false)
            getResultText.launch(mIntent)
        }

        binding.btnGofavorite.setOnClickListener {
            val favoriteList = ArrayList<Data>()
            for(i:Int in 0 until  dataList.size) {
                if(dataList[i].favorite) favoriteList.add(dataList[i])
            }
            val mIntent = Intent(this@MainActivity, FavoriteActivity::class.java).apply {
                putExtra("favoriteList",favoriteList)   // troubleshooting
            }
            startActivity(mIntent)
        }
    }
}