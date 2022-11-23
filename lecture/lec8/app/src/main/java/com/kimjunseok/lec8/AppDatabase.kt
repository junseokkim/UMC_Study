package com.kimjunseok.lec8

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [User::class], version=1)
// 추상화 시켜줘야함
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var appDatabase: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase? {
            if(appDatabase == null) {
                synchronized(AppDatabase::class.java) {
                    appDatabase = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app-database" // 여러개의 데이터베이스를 만들려면 이름은 항상 다르게 해줘야함
                    ).allowMainThreadQueries().build()
                }
            }
            return appDatabase
        }
    }
}