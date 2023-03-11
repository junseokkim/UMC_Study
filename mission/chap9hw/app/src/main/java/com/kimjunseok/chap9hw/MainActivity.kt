package com.kimjunseok.chap9hw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.GsonBuilder
import com.kimjunseok.chap9hw.databinding.ActivityMainBinding
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var retrofit: Retrofit
    var baseUrl = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate((layoutInflater))
        setContentView(binding.root)

        binding.apply {
            // 날씨 버튼 클릭 시
            btnWeather.setOnClickListener {
                // 초단기예보조회 API
                baseUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/"
                setting()

                val apiService = retrofit.create(ApiService::class.java)
                // 행 개수 3, 2022년 11월 29일 06:30분 기준으로 설정
                val call = apiService.getUltraSrtFcst("JSON", 3, 1,
                    "20221129", "0630", "55", "127")
                call.enqueue(object : retrofit2.Callback<WEATHER>{
                    override fun onResponse(call: Call<WEATHER>, response: Response<WEATHER>) {
                        if(response.isSuccessful) {
                            val responseData = response.body()
                            if(responseData != null) {
                                Log.d("TEST","Response\n${responseData.response.header.resultCode} Message : ${responseData.response.header.resultMsg}\n")
                                showData((responseData))
                            }
                        } else {
                            Log.w("TEST","Response Not Successful ${response.body()}")
                        }
                    }

                    override fun onFailure(call: Call<WEATHER>, t: Throwable) {
                        Log.d("TEST","Error!",t)
                    }
                })
            }
            // 미세먼지 버튼 클릭 시
            btnDust.setOnClickListener {
                // 시도별 실시간 측정정보 조회 상세기능명세시도별 실시간 측정정보 조회 상세기능명세 API
                baseUrl = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/"
                setting()
                val apiService2 = retrofit.create(ApiService2::class.java)
                // 행 개수 3, 울산시, 1.0버전으로 설정
                val call2 = apiService2.getCtprvnRltmMesureDnsty("울산",1,3,
                    "json","MP/PZKljnnEeyzcpS63Gp64iWKeTktEfUPgTLTxUkeWfS9PhYf9ru7EG/cYyzR6oRkPIabAduj3ucpq0psgPHQ==","1.0")
                call2.enqueue(object: retrofit2.Callback<DUST>{
                    override fun onResponse(call: Call<DUST>, response: Response<DUST>) {
                        if(response.isSuccessful) {
                            val responseData = response.body()

                            if(responseData != null) {
                                Log.d("TEST","Response\n${responseData.response.header.resultCode} Message : ${responseData.response.header.resultMsg}\n")
                                showData2(responseData)
                            } else {
                                Log.w("TEST","Response Not Successful ${response.body()}")

                            }
                        }
                    }

                    override fun onFailure(call: Call<DUST>, t: Throwable) {
                        Log.d("TEST","Error!",t)
                    }

                })
            }
            // 코로나 버튼 클릭 시
            btnCovid.setOnClickListener {
                // 코로나19 국내발생현황(최근 7일간 확진) 조회 API
                baseUrl = "http://apis.data.go.kr/1790387/covid19CurrentStatusConfirmations/"
                setting()
                val apiService3 = retrofit.create(ApiService3::class.java)
                // 지정해줄 query string 없음
                val call3 = apiService3.covid19CurrentStatusConfirmationsJson()
                call3.enqueue(object : retrofit2.Callback<COVID19>{
                    override fun onResponse(call: Call<COVID19>, response: Response<COVID19>) {
                        if(response.isSuccessful) {
                            val responseData = response.body()

                            if(responseData != null) {
                                Log.d("TEST","Response\n${responseData.response.resultCode} Message : ${responseData.response.resultMsg}\n")
                                showData3(responseData)
                            }
                        } else {
                            Log.w("TEST","Response Not Successful ${response.body()}")
                        }
                    }

                    override fun onFailure(call: Call<COVID19>, t: Throwable) {
                        Log.d("TEST","Error!",t)
                    }
                })
            }
        }
    }
    fun showData(weather: WEATHER) {
        val list = weather.response.body.items.item
        for(i in list.indices) {
            Log.d("TEST", "${list[i]}\n")

        }
    }
    fun showData2(dust: DUST) {
        val list = dust.response.body.items
        for (i in list.indices) {
            Log.d("TEST", "${list[i]}\n")
        }
    }
    fun showData3(covid: COVID19) {
        val list = covid.response.result[0]
        Log.d("TEST", "${list.mmdd1} : ${list.cnt1}")
        Log.d("TEST", "${list.mmdd2} : ${list.cnt2}")
        Log.d("TEST", "${list.mmdd3} : ${list.cnt3}")
        Log.d("TEST", "${list.mmdd4} : ${list.cnt4}")
        Log.d("TEST", "${list.mmdd5} : ${list.cnt5}")
        Log.d("TEST", "${list.mmdd6} : ${list.cnt6}")
        Log.d("TEST", "${list.mmdd7} : ${list.cnt7}")
    }
    private fun setting() {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .cookieJar(JavaNetCookieJar(CookieManager()))
            .addInterceptor(interceptor)
//            .connectTimeout(CONNECT_TIMEOUT_SEC, TimeUnit.SECONDS)
            .build()
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

}

