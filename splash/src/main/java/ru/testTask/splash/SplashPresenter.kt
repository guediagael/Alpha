package ru.testTask.splash

import android.util.Log
import ru.testTask.model.FeedItem


class SplashPresenter(private val splashRouter: SplashRouter, private val splashInteractor: SplashInteractor) {

    interface SplashPresenterListener{
        fun onError(errorMessage: String)
    }

    private lateinit var splashPresenterListener: SplashPresenterListener


    fun onCreate(splashPresenterListener: SplashPresenterListener){
        Log.i("creating presenter", "creating")
        this.splashPresenterListener = splashPresenterListener
        splashInteractor.getFeed()
            .doOnNext { item: FeedItem? -> run { Log.i("new item: ", item.toString()) } }
            .doOnError{t: Throwable-> kotlin.run { Log.e("fetch news error",t.message, t) }}
            .subscribe()
    }

}