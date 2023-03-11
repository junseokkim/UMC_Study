package com.kimjunseok.chap9hw

import retrofit2.Call
import retrofit2.http.GET

interface ApiService3 {
    @GET("covid19CurrentStatusConfirmationsJson?serviceKey=MP%2FPZKljnnEeyzcpS63Gp64iWKeTktEfUPgTLTxUkeWfS9PhYf9ru7EG%2FcYyzR6oRkPIabAduj3ucpq0psgPHQ%3D%3D")
    fun covid19CurrentStatusConfirmationsJson(): Call<COVID19>
}