package com.example.loginusingmvp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [person::class, Data::class], exportSchema = false, version = 2)
abstract class DataBase : RoomDatabase() {

    companion object {
        private const val dbname: String = "person_db"
        private var database: DataBase? = null

        @Synchronized
        fun getInstance(context: Context): DataBase {
            if (database == null) {
                database =
                    Room.databaseBuilder<DataBase>(
                        context.applicationContext,
                        DataBase::class.java,
                        dbname
                    ).fallbackToDestructiveMigration().build()
            }
            return database as DataBase
        }
    }

    abstract fun personDao(): personDao

    abstract fun dataDao(): DataDao
}