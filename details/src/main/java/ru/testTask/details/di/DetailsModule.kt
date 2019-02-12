package ru.testTask.details.di

import dagger.Module
import dagger.Provides
import ru.testTask.core.rx.SchedulerProvider
import ru.testTask.details.DetailsContract
import ru.testTask.details.DetailsInteractor
import ru.testTask.details.DetailsPresenter

@Module
class DetailsModule {

    @Provides
    fun provideDetailsPresenter(detailsInteractor: DetailsInteractor, schedulerProvider: SchedulerProvider): DetailsContract.Presenter{
        return DetailsPresenter(schedulerProvider, detailsInteractor)
    }
}