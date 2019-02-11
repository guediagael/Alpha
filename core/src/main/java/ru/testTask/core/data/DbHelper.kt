package ru.testTask.core.data

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import ru.testTask.model.FeedItem
import ru.testTask.model.WebViewItem

interface DbHelper {
    fun getFeedItems():Flowable<List<FeedItem>>
    fun getBookMarks():Flowable<List<WebViewItem>>
    fun bookmarkPage(webViewItem: WebViewItem): Completable
    fun addItemstoTheDb(feedItem: List<FeedItem>): Completable
    fun getBookmark(url: String): Single<WebViewItem>
}