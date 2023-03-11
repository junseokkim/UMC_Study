package com.kimjunseok.chap9hw

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService2 {
    @GET("getCtprvnRltmMesureDnsty")
    fun getCtprvnRltmMesureDnsty(
        @Query("sidoName") sido_name: String,
        @Query("pageNo") page_no: Int,
        @Query("numOfRows") num_of_rows: Int,
        @Query("returnType") return_type: String,
        @Query("serviceKey") service_key: String,
        @Query("ver") ver: String
    ): Call<DUST>
}