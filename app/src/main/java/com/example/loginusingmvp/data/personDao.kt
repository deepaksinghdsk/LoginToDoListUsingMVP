package com.example.loginusingmvp.data

import androidx.room.*

@Dao
public interface personDao {

    @Query("select * from person")
    fun getPersonLIst(): List<person>

    @Insert
    fun insert(person: person)

    @Delete
    fun delete(person: person)

    @Update
    fun update(person: person)
}