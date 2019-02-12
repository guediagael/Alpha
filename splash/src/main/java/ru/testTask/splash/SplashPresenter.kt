package ru.testTask.splash

import android.content.Context
import android.util.Log
import ru.testTask.core.rx.SchedulerProvider
import ru.testTask.model.FeedItem


class SplashPresenter(
    private val splashRouter: SplashRouter,
    private val schedulerProvider: SchedulerProvider,
    private val splashInteractor: SplashInteractor
) : SplashContract.Presenter {

    companion object {

        val TAG = SplashPresenter::class.java.simpleName
    }

    private lateinit var splashPresenterListener: SplashContract.View

    override fun onCreate(splashView: SplashContract.View) {
        this.splashPresenterListener = splashView
        splashInteractor.checkIsFirstConnection()
            .doOnSuccess { isFirstconnection ->
                kotlin.run {
                    Log.d(TAG, "is first connection $isFirstconnection")
                    if (isFirstconnection) {

                        splashInteractor.getFeed {
                            splashPresenterListener.onLoaded() }
//
                    } else {
                        splashView.onLoaded()
                    }
                }
            }.subscribe()

    }


    override fun onComplete(context: Context) {
        startRefreshService()
        splashRouter.openMain(context)
    }


    private fun startRefreshService() {
        //startService if success then set is no more the first connection

    }


}