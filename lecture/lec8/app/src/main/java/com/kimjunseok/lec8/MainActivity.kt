package com.kimjunseok.lec8

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kimjunseok.lec8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        // sharedpreference
        // val shareDPrefs = getSharedPreferences(packageName)
        /*
        val sharedPrefs = getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()

        // 이 코드는 무시(초기화 용)
        editor.putString("bex","")
        editor.apply()
        // 이 코드는 무시(초기화 용)

        editor.putString("bex","android")
        val beforeapplyvalue = sharedPrefs.getString("bex", "") // 두번째 인자는 없을 때 받아오는 값
        Log.d("SP Before", "${beforeapplyvalue}")

        editor.apply()

        val spvalue = sharedPrefs.getString("bex", "") // 두번째 인자는 없을 때 받아오는 값
        Log.d("SP", "${spvalue}")
         */

        val roomDB = AppDatabase.getInstance(this)

        if(roomDB != null) {
            // 앱이 실행되면 아래 값들은 다음에 실행해도 저장되어 있음
            // val user = User("블루",23)
            // roomDB.userDao().insert(user)

            // roomDB.userDao().updateNameByUserId(2, "하우")

            // val deletedUser = User("",0,1)
            // roomDB.userDao().delete(deletedUser)

            val user = roomDB.userDao().selectByUserId(2)
            Log.d("DB","User Id 2 : $user")

            val userList = roomDB.userDao().seletAll()
            Log.d("DB","User List : ${userList}")
        }

    }
}