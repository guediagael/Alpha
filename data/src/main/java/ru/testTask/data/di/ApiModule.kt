package ru.testTask.data.di

import dagger.Module
import dagger.Provides
import ru.testTask.core.di.ApplicationScope
import ru.testTask.data.remote.api.FeedApi

@Module
class ApiModule {



    @Provides
    @ApplicationScope
    fun provideFeedApi(): FeedApi = FeedApi.create()

}