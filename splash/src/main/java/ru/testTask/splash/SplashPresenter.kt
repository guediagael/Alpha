package ru.testTask.splash

import android.content.Context
import android.util.Log
import androidx.work.*
import ru.testTask.core.rx.SchedulerProvider
import java.time.Duration
import java.util.concurrent.TimeUnit


class SplashPresenter(
    private val splashRouter: SplashRouter,
    private val schedulerProvider: SchedulerProvider,
    private val splashInteractor: SplashInteractor
) : SplashContract.Presenter {

    companion object {

        val TAG = SplashPresenter::class.java.simpleName
    }

    private lateinit var mSplashPresenterListener: SplashContract.View
    private lateinit var mWorkerManager : WorkManager

    override fun onCreate(splashView: SplashContract.View) {
        this.mSplashPresenterListener = splashView
        splashInteractor.checkIsFirstConnection()
            .doOnSuccess { isFirstconnection ->
                kotlin.run {
                    Log.d(TAG, "is first connection $isFirstconnection")
                    if (isFirstconnection) {

                        splashInteractor.getFeed {
                            mSplashPresenterListener.onLoaded() }
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
        val taskContraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workRequest  = PeriodicWorkRequestBuilder<DownloadWorker>(5, TimeUnit.MINUTES)
        workRequest.setConstraints(taskContraint).build()
        val buildedWorkRequest = workRequest.build()
        WorkManager.getInstance().enqueue(buildedWorkRequest)

    }


}