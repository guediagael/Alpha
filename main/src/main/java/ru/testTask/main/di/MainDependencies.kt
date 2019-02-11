package ru.testTask.main.di

import dagger.Provides
import ru.testTask.core.interfaces.ComponentDependencies
import ru.testTask.core.repo.FetchDataRepo
import ru.testTask.main.MainRouter

interface MainDependencies: ComponentDependencies {

    fun provideMainRouter(): MainRouter
    fun provideMainRepo(): FetchDataRepo
}