package ru.testTask.splash

import android.util.Log
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import ru.testTask.core.data.repo.FetchDataRepo
import ru.testTask.core.rx.SchedulerProvider
import ru.testTask.model.FeedItem
import javax.inject.Inject

class SplashInteractor @Inject constructor(private val fetchDataRepo: FetchDataRepo) {


    fun getFeed(listener: ()-> Unit): Disposable{
       return fetchDataRepo.fetchFromApi()
            .doOnNext{ items-> Log.d("itereator", items.toString())}
            .doOnComplete {
                Log.d("completed: ", "ttt")
                listener()

            }
            .subscribe()

    }


    fun checkIsFirstConnection():Single<Boolean>{
        return  fetchDataRepo.isFirstAppUseUse()
    }

    fun setUserUsedTheApp(){
        fetchDataRepo.setIsNotTheFirstUse()
    }



}