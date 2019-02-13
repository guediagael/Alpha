package ru.testTask.splash

import android.content.Context
import android.support.annotation.CallSuper
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception

class DownloadWorker(context: Context, params: WorkerParameters,private val splashInteractor: SplashInteractor)
    : Worker(context, params){

    companion object {
        val TAG = DownloadWorker::class.java.simpleName
    }
    override fun doWork(): Result {
        try{


            splashInteractor.setUserUsedTheApp()
            splashInteractor.getFeed { run {
                Log.d(TAG, "updated")

            } }
            return Result.success()
        }catch (e : Throwable){
            Log.e(TAG, e.message, e)
            return Result.failure()
        }
    }

}