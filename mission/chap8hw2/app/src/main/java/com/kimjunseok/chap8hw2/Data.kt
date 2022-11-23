package com.kimjunseok.chap8hw2

data class Data (
    val title: String,
    val content: String,
    var favorite: Boolean
) : java.io.Serializable