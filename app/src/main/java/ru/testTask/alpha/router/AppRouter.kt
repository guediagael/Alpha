package ru.testTask.alpha.router

import android.content.Context
import ru.testTask.main.MainRouter
import ru.testTask.splash.SplashRouter


interface AppRouter : SplashRouter, MainRouter {

    fun openSplash(context: Context)
}