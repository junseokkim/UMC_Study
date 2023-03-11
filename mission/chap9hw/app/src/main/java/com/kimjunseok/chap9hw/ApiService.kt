package com.kimjunseok.chap9hw

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("getUltraSrtFcst?serviceKey=MP%2FPZKljnnEeyzcpS63Gp64iWKeTktEfUPgTLTxUkeWfS9PhYf9ru7EG%2FcYyzR6oRkPIabAduj3ucpq0psgPHQ%3D%3D")
    fun getUltraSrtFcst(
        @Query("dataType")data_type: String,
        @Query("numOfRows")num_of_rows: Int,
        @Query("pageNo")page_no: Int,
        @Query("base_date")base_date: String,
        @Query("base_time")base_time: String,
        @Query("nx")nx: String,
        @Query("ny")ny: String
    ): Call<WEATHER>
}