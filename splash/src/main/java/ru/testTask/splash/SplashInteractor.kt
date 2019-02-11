package ru.testTask.splash

import io.reactivex.Flowable
import ru.testTask.core.repo.FetchDataRepo
import ru.testTask.core.rx.SchedulerProvider
import ru.testTask.model.FeedItem
import javax.inject.Inject

class SplashInteractor @Inject constructor(private val fetchDataRepo: FetchDataRepo,private val schedulerProvider: SchedulerProvider) {


    fun getFeed(): Flowable<FeedItem>{
        return fetchDataRepo.fetchFromApi().subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui())
    }
}