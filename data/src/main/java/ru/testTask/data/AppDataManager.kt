package ru.testTask.data

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import ru.testTask.core.data.DataManager
import ru.testTask.core.data.DbHelper
import ru.testTask.core.data.SharedPreferencesHelper
import ru.testTask.model.FeedItem
import ru.testTask.model.WebViewItem

class AppDataManager(
    private val sharedPreferencesHelper: SharedPreferencesHelper,
    private val dbHelper: DbHelper
) : DataManager {
    override fun setIfNotFirstConnection() {
        sharedPreferencesHelper.setIfNotFirstConnection()
    }

    override fun isFirstConnection(): Boolean = sharedPreferencesHelper.isFirstConnection()

    override fun getFeedItems(): Flowable<List<FeedItem>> = dbHelper.getFeedItems()

    override fun getBookMarks(): Flowable<List<WebViewItem>> = dbHelper.getBookMarks()

    override fun bookmarkPage(webViewItem: WebViewItem): Completable = dbHelper.bookmarkPage(webViewItem)

    override fun addItemstoTheDb(feedItem: List<FeedItem>): Completable = dbHelper.addItemstoTheDb(feedItem)

    override fun getBookmark(url: String): Single<WebViewItem> = dbHelper.getBookmark(url)

    override fun removeBookmark(webViewItem: WebViewItem): Completable =dbHelper.removeBookmark(webViewItem)

}