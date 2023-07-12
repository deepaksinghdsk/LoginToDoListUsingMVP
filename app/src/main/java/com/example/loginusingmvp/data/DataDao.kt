package com.example.loginusingmvp.data

import androidx.room.*

@Dao
interface DataDao {

    @Query("select * from data where username = :user")
    fun getPersonLIst(user: String): MutableList<Data>

    @Insert
    fun insert(data: Data): Long

    @Delete
    fun delete(data: Data): Int

    @Update
    fun update(data: Data)
}
