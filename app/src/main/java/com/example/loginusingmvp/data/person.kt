package com.example.loginusingmvp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "person")
class person(
    @PrimaryKey(autoGenerate = true)
    private var id: Int?,
    @ColumnInfo(name = "username")
    private var username: String,
    @ColumnInfo(name = "password")
    private var password: String
) {

    /*@PrimaryKey(autoGenerate = true)
    private var id:Int? = null
    @ColumnInfo(name = "username")
    private var username:String? = null
    @ColumnInfo(name = "password")
    private var password:String? = null*/
    @Ignore
    constructor(username: String, password: String) : this(
        null,
        username,
        password
    )

    fun getUsername(): String {
        return username
    }

    fun getPassword(): String {
        return password
    }

    fun getId(): Int? {
        return id
    }
}