package com.example.loginusingmvp.presenter

import android.widget.EditText
import com.example.loginusingmvp.data.Data

interface Presenters {

    interface Presenter1 {
        //View interfaces are declared in each presenter separately according to there needs
        interface View {
            fun reset(user: Boolean, pass: Boolean)
            fun navigateNextScreen()
            fun error(message: String, errorIn: EditText)
        }

        fun insert(username: String, password: String)
        fun verify(username: String, password: String)
        fun loadNextScreen()
    }

    interface Presenter2 {
        //View interfaces are declared in each presenter separately according to there needs
        interface View {
            fun reset()
            fun setAdapter(data: MutableList<Data>)
            fun addInAdapter(data: Data)
            fun removeFromAdapter(data: Data)
        }

        fun getDataFromTable(user: String)
        fun insert(text: String, username: String?)
        fun delete(text: String, username: String?, id: Int)
    }
}