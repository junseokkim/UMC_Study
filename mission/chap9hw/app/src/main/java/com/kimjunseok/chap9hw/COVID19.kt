package com.kimjunseok.chap9hw

data class COVID19(
    val response: Response3
)
data class Response3(
    val resultMsg: String,
    val result: List<Result>,
    val resultCnt: String,
    val resultCode: String
)
data class Result(
    val mmdd1: String,
    val cnt1: String,
    val mmdd2: String,
    val cnt2: String,
    val mmdd3: String,
    val cnt3: String,
    val mmdd4: String,
    val cnt4: String,
    val mmdd5: String,
    val cnt5: String,
    val mmdd6: String,
    val cnt6: String,
    val mmdd7: String,
    val cnt7: String
)

