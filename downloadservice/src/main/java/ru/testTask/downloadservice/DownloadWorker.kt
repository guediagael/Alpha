package ru.testTask.downloadservice

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class DownloadWorker(private val context: Context, params: WorkerParameters) : Worker(context, params) {

    companion object {
        val TAG = DownloadWorker::class.java.simpleName

    }




    override fun doWork(): Result {
        try {

            DownloadWork(context).updateDb()


            return Result.success()
        } catch (e: Throwable) {
            Log.e(TAG, e.message, e)
            return Result.failure()
        }
    }

}