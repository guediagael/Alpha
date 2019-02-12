package ru.testTask.data.di

import ru.testTask.core.data.DataManager
import ru.testTask.core.interfaces.ComponentDependencies
import ru.testTask.core.rx.SchedulerProvider

interface ApiModuleDependcies: ComponentDependencies {
    fun provideDataManager(): DataManager
    fun provideSchedulerProvider(): SchedulerProvider
}