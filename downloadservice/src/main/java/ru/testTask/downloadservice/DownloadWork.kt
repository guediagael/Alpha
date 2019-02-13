package ru.testTask.downloadservice

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import ru.testTask.data.BuildConfig
import ru.testTask.data.local.db.AppDataBase
import ru.testTask.data.local.db.NewsItem
import ru.testTask.data.remote.ApiClient
import ru.testTask.data.remote.api.FeedApi
import java.sql.SQLDataException

class DownloadWork(context: Context) {
    companion object {
        val TAG = DownloadWork::class.java.simpleName
    }

    private val appDataBase: AppDataBase = Room
        .databaseBuilder(context, AppDataBase::class.java, BuildConfig.APPLICATION_ID)
        .allowMainThreadQueries()
        .build()
    private val feedApi: FeedApi = FeedApi.create()

    private val preferenceEditor = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE).edit()

    fun updateDb() {
        feedApi.getRssFeed()
            .doOnNext { rss ->
            run {
                val items = rss.mChannel?.mItems
                val newsItems = items?.map { NewsItem(it.mLink!!, it.mTitle) }
                newsItems?.let {
                    val numberOfAddedItems =
                        items.let { appDataBase.newsItemDao().insertNewItems(*newsItems.toTypedArray()) }
                    if (!newsItems.isEmpty() && numberOfAddedItems.size.compareTo(0) == 0)
                        throw SQLDataException()
                }
            }
        }
            .doOnError { t ->
                Log.e(TAG, t.message, t)
            }
            .doOnComplete { preferenceEditor.putBoolean("isUserFirstLogin",false).commit() }
            .subscribe()
    }

}