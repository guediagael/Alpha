package ru.testTask.main.di

import android.content.Context
import ru.testTask.core.data.DataManager
import ru.testTask.core.data.repo.FetchDataRepo
import ru.testTask.core.data.repo.LoadDataRepo
import ru.testTask.core.interfaces.ComponentDependencies
import ru.testTask.core.rx.SchedulerProvider
import ru.testTask.main.MainRouter

interface MainDependencies: ComponentDependencies {

    fun provideMainRouter(): MainRouter
    fun provideMainRepo(): LoadDataRepo
    fun provideSchedulerProvider(): SchedulerProvider
    fun provideContext(): Context
}