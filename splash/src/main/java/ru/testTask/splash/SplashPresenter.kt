package ru.testTask.splash

import android.content.Context
import android.util.Log
import ru.testTask.model.FeedItem
import javax.inject.Inject


class SplashPresenter(
    private val splashRouter: SplashRouter,
    private val splashInteractor: SplashInteractor
): SplashContract.Presenter {

    private lateinit var splashPresenterListener: SplashContract.View


    override fun onCreate(splashView: SplashContract.View){
        Log.i("creating presenter", "creating")
        this.splashPresenterListener = splashView
        splashInteractor.getFeed()
            .doOnNext { item: List<FeedItem>? -> run {
                Log.i("new item: ", item.toString()) }

            }
            .doOnError{t: Throwable-> kotlin.run { Log.e("fetch news error",t.message, t) }}
            .doOnComplete {  }
            .subscribe()
    }


    override fun onComplete(context: Context) {
        startRefreshService()
        splashRouter.openMain(context)
    }


    private fun startRefreshService(){

    }



}