package ru.testTask.data.di

import ru.testTask.core.data.DataManager
import ru.testTask.core.interfaces.ComponentDependencies

interface ApiModuleDependcies: ComponentDependencies {
    fun provideDataManager(): DataManager
}