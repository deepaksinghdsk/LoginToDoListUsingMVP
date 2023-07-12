package com.example.loginusingmvp

import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutor(val diskIO: Executor) {

    //for singleton instantiation
    companion object {
        var appExecutor: AppExecutor? = null

        fun getInstance(): AppExecutor {
            if (appExecutor == null) {
                appExecutor = AppExecutor(Executors.newSingleThreadExecutor())
            }
            return appExecutor as AppExecutor
        }
    }
}