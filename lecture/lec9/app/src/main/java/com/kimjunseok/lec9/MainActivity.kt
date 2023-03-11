package com.kimjunseok.lec9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kimjunseok.lec9.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val viewBinding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        // 웹 브라우저를 여는 과정
        val retrofit = Retrofit.Builder()
            .baseUrl("https://prodmp.eric-rc.shop/") // 어떤 서버와 통신하느냐에 따라서 baseUrl은 달라짐
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // 어떤 주소를 입력해주는 것
        val apiService = retrofit.create(ApiService::class.java)

        // 입력한 주소 중 하나로 연결 시도!
        apiService.getCheckEmail("abc@abc.com").enqueue(object: Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if(response.isSuccessful) {
                    val responseData = response.body()
                    if(responseData != null) {
                        Log.d("Retrofit", "Response\n${responseData.code} Message: ${responseData.message}")
                    }
                } else {
                    Log.w("Retrofit","Response Not Successful ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.d("Retrofit","Error!",t)
            }

        })
    }

}