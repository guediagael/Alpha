package ru.testTask.main.di

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import dagger.Module
import dagger.Provides
import ru.testTask.core.rx.SchedulerProvider
import ru.testTask.main.*
import ru.testTask.model.FeedItem

@Module
class MainModule {

    @Provides
    fun provideMainRecyclerViewAdapter(): MainRecyclerViewAdapter {

        return MainRecyclerViewAdapter(arrayListOf())
    }

    @Provides
    fun provideLinearLayoutManager(context: Context):LinearLayoutManager{
        return LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
    }
    @Provides
    fun provideMainPresenter(mainInteractor: MainInteractor, mainRouter: MainRouter, schedulerProvider: SchedulerProvider): MainContract.Presenter{
        return  MainPresenter(mainRouter, schedulerProvider,mainInteractor)
    }
}