package com.kimjunseok.chap9hw

data class DUST(
    val response: Response2
)
data class Response2(
    val body: Body2,
    val header: Header2
)
data class Header2(
    val resultMsg: String,
    val resultCode: String
)
data class Body2(
    val totalCount: Int,
    val items: List<Items2>,
    val pageNo: Int,
    val numOfRows: Int
)
data class Items2(
    val pm25Value: String,
    val sidoName: String,
    val dataTime: String,
    val stationName: String
)