package com.kimjunseok.chap8hw2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Memo::class], version=1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun memoDao(): MemoDao

    companion object {
        private var appDataBase: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase? {
            if(appDataBase == null) {
                synchronized(AppDatabase::class.java) {
                    appDataBase = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app-database"
                    ).allowMainThreadQueries().build()
                }
            }
            return appDataBase
        }
    }
}