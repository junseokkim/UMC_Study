package com.kimjunseok.lec8

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM User")
    fun seletAll(): List<User>

    @Query("SELECT * FROM User WHERE userId = :userId")
    fun selectByUserId(userId: Int): User

    @Query("SELECT * FROM User WHERE name = :name")
    fun selectByUserName(name: String): List<User>

    @Query("UPDATE User SET name = :name WHERE userId = :userId")
    fun updateNameByUserId(userId: Int, name: String)
}