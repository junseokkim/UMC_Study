package com.kimjunseok.chap6hw

data class Alarm(
    val day: String,
    val hour: String,
    val minute: String,
    var isChecked:Boolean = false
)
