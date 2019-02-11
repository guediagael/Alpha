package ru.testTask.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.testTask.core.di.ApplicationScope
import ru.testTask.core.repo.FetchDataRepo
import ru.testTask.core.repo.LoadDataRepo
import ru.testTask.data.AppRepoImpl
import ru.testTask.data.remote.api.FeedApi
import javax.inject.Singleton

@Module
class RepoModule {


    @Provides
    @ApplicationScope
    fun provideSplashRepo(feedApi: FeedApi) : FetchDataRepo = AppRepoImpl(feedApi)


    @Provides
    @ApplicationScope
    fun provideMainRepo(feedApi: FeedApi) : LoadDataRepo = AppRepoImpl(feedApi)
}