package com.kimjunseok.lec8

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity //해당 코드를 붙여줘야 RoomDB에서 사용가능
data class User(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "age") val apge: Int,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "userId") val userId: Int = 0
)
