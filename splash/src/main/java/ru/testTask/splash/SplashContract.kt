package ru.testTask.splash

import android.content.Context

interface SplashContract {
    interface View {
        fun onError(errorMessage: String)
        fun onLoaded()
    }

    interface Presenter{
        fun onCreate(view : SplashContract.View)
        fun onComplete(context: Context)
    }
}