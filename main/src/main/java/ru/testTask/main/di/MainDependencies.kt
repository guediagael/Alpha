package ru.testTask.main.di

import ru.testTask.core.data.DataManager
import ru.testTask.core.data.repo.FetchDataRepo
import ru.testTask.core.interfaces.ComponentDependencies
import ru.testTask.main.MainRouter

interface MainDependencies: ComponentDependencies {

    fun provideMainRouter(): MainRouter
    fun provideMainRepo(): FetchDataRepo
}