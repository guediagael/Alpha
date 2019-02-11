package ru.testTask.splash

import io.reactivex.Flowable
import io.reactivex.Single
import ru.testTask.core.data.repo.FetchDataRepo
import ru.testTask.core.rx.SchedulerProvider
import ru.testTask.model.FeedItem
import javax.inject.Inject

class SplashInteractor @Inject constructor(private val fetchDataRepo: FetchDataRepo, private val schedulerProvider: SchedulerProvider) {


    fun getFeed(): Flowable<List<FeedItem>>{
        return fetchDataRepo.fetchFromApi().subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui())
    }

    fun checkIsFirstConnection():Single<Boolean>{
        return  fetchDataRepo.isFirstAppUseUse()
    }
}