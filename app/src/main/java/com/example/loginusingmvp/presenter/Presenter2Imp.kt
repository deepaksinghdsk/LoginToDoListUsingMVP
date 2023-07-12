package com.example.loginusingmvp.presenter

import android.content.Context
import android.util.Log
import com.example.loginusingmvp.AppExecutor
import com.example.loginusingmvp.data.Data
import com.example.loginusingmvp.data.DataBase

class Presenter2Imp(c: Context, private val view: Presenters.Presenter2.View) :
    Presenters.Presenter2 {

    private val database = DataBase.getInstance(c)

    override fun insert(text: String, username: String?) {
        AppExecutor.getInstance().diskIO.execute {
            val data = Data(text, username)
            val id = database.dataDao().insert(data)
            data.setId(id.toInt())
            view.reset()
            view.addInAdapter(data)
        }
    }

    override fun getDataFromTable(user: String) {
        AppExecutor.getInstance().diskIO.execute {
            val list = database.dataDao().getPersonLIst(user)
            view.setAdapter(list)
            for (it in list) {
                Log.d("tag", "Id : ${it.getId()} and text = ${it.getData()}")
            }
        }
    }

    override fun delete(text: String, username: String?, id: Int) {
        AppExecutor.getInstance().diskIO.execute {
            val data = Data(id, text, username)
            val affectedRows = database.dataDao().delete(data)
            Log.d(
                "presenter",
                "Number of affected rows = $affectedRows\nDeleting task text = $text and username = $username"
            )

            view.removeFromAdapter(data)
        }
    }
}