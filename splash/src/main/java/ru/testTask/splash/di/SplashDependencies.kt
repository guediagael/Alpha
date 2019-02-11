package ru.testTask.splash.di

import ru.testTask.core.interfaces.ComponentDependencies
import ru.testTask.core.repo.FetchDataRepo
import ru.testTask.core.rx.SchedulerProvider
import ru.testTask.splash.SplashRouter

interface SplashDependencies : ComponentDependencies {

    fun provideSplashRouter(): SplashRouter

    fun provideSplashRepo(): FetchDataRepo

    fun provideSchedulerProvider(): SchedulerProvider
}