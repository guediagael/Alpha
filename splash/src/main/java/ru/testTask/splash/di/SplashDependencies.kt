package ru.testTask.splash.di

import ru.testTask.core.interfaces.ComponentDependencies
import ru.testTask.splash.SplashRouter

interface SplashDependencies : ComponentDependencies {

    fun provideSplashRouter(): SplashRouter
}