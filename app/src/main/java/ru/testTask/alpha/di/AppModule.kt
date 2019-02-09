package ru.testTask.alpha.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.testTask.core.di.ApplicationScope

@Module
class AppModule (private val context: Context){
    @ApplicationScope
    @Provides
    fun provieCOntext(): Context = context
}