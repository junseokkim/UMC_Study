package com.kimjunseok.chap8hw2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MemoDao {
    @Insert
    fun insert(memo: Memo)

    @Delete
    fun delete(memo: Memo)

    @Query("SELECT * FROM Memo")
    fun selectAll(): List<Memo>

    @Query("SELECT * FROM Memo WHERE memoId = :memoId")
    fun selectByMemoId(memoId: Int): Memo

    @Query("SELECT * FROM Memo WHERE title = :title")
    fun selectByMemoTitle(title: String): Memo

    @Query("SELECT memoId FROM Memo WHERE title = :title")
    fun getIdByMemoTitle(title: String): Int

    @Query("UPDATE Memo SET title = :title WHERE memoId = :memoId")
    fun updateTitleByMemoId(memoId: Int, title: String)

    @Query("UPDATE Memo SET content = :content WHERE memoId = :memoId")
    fun updateContentByMemoId(memoId: Int, content: String)
}