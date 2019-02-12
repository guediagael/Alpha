package ru.testTask.details.di

import android.content.Context
import ru.testTask.core.data.repo.WebViewRepo
import ru.testTask.core.interfaces.ComponentDependencies
import ru.testTask.core.rx.SchedulerProvider

interface DetailsDependencies: ComponentDependencies {
    fun provideWebViewRepo(): WebViewRepo
    fun provideSchedulerProvider(): SchedulerProvider
}