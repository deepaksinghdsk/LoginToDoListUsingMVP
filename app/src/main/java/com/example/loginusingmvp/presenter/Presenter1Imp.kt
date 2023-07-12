package com.example.loginusingmvp.presenter

import android.content.Context
import com.example.loginusingmvp.AppExecutor
import com.example.loginusingmvp.data.DataBase
import com.example.loginusingmvp.data.person
import com.example.loginusingmvp.viewImpls.MainActivity

class Presenter1Imp(private val view: Presenters.Presenter1.View, context: Context) :
    Presenters.Presenter1 {

    private var database: DataBase = DataBase.getInstance(context)
    private val executor: AppExecutor =
        AppExecutor.getInstance()

    override fun insert(username: String, password: String) {
        executor.diskIO.execute {
            var accountExists = false
            val list: List<person> = database.personDao().getPersonLIst()

            for (data: person in list)
                if (data.getUsername() == username) {
                    view.error(
                        "$username already exists",
                        MainActivity.username
                    )
                    accountExists = true
                    break
                }

            if (!accountExists) {
                database.personDao().insert(person(username, password))
                view.navigateNextScreen()
            }
        }

    }

    override fun verify(username: String, password: String) {
        executor.diskIO.execute {
            val list: List<person> = database.personDao().getPersonLIst()
            var usernameFound = false

            for (data: person in list) {
                if (data.getUsername() == username) {
                    if (data.getPassword() == password) {
                        view.navigateNextScreen()
                        usernameFound = true
                        break
                    } else {
                        view.error(
                            "Incorrect password!",
                            MainActivity.password
                        )
                        usernameFound = true
                        break
                    }
                }
            }

            if (!usernameFound)
                view.error(
                    "User does't exist",
                    MainActivity.username
                )
        }
    }

    override fun loadNextScreen() {

    }
}