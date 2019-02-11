package ru.testTask.alpha.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.testTask.core.di.ApplicationScope
import ru.testTask.alpha.AppSchedulerProvider
import ru.testTask.core.rx.SchedulerProvider

@Module
class AppModule (private val context: Context){
    @ApplicationScope
    @Provides
    fun provideContext(): Context = context

    @ApplicationScope
    @Provides
    fun provideScheduler(): SchedulerProvider = AppSchedulerProvider()
}