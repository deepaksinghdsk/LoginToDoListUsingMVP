package com.example.loginusingmvp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "data")
class Data(
    @PrimaryKey(autoGenerate = true)
    private var id: Int?,
    @ColumnInfo(name = "text")
    private var data: String,
    @ColumnInfo(name = "username")
    private var username: String?
) {

    @Ignore
    constructor(data: String, username: String?) : this(
        null,
        data,
        username
    )

    fun getData(): String {
        return data
    }

    fun getUsername(): String? {
        return username
    }

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }
}