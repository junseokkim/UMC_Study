package com.kimjunseok.lec9

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("app/users/email-check") // 맨 앞 '/' 와 Query String을 생략해서 적어야함
    fun getCheckEmail(
        @Query("email") email: String
     ): Call<Response>
}