package ru.testTask.data.local.db

import android.util.Log
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import ru.testTask.core.data.DbHelper
import ru.testTask.model.FeedItem
import ru.testTask.model.WebViewItem
import javax.inject.Inject

class AppDbHelper @Inject constructor(private val dataBase: AppDataBase) : DbHelper {
    companion object {
        val TAG = AppDbHelper::class.java.simpleName
    }

    override fun getFeedItems(): Flowable<List<FeedItem>> {
        val feedItems = arrayListOf<FeedItem>()
        dataBase.newsItemDao().getNewsFeed().map { feedItems.add(FeedItem(it.title ?: "Без названия", it.url)) }
        return Flowable.fromCallable { feedItems }
    }

    override fun getBookMarks(): Flowable<List<WebViewItem>> {
        val webViewItems = arrayListOf<WebViewItem>()
        dataBase.bookmarkedPageDao().getAllBookmarkedPages()
            .map { webViewItems.add(WebViewItem(it.link, it.html ?: "Пустая страница")) }
        return Flowable.fromCallable{webViewItems}
    }

    override fun bookmarkPage(webViewItem: WebViewItem): Completable {
        val bookmarkedPage = BookmarkedPage(webViewItem.url, webViewItem.content)
        return Completable.fromCallable { dataBase.bookmarkedPageDao().insertNewBookmark(bookmarkedPage) }
    }

    override fun addItemstoTheDb(feedItem: List<FeedItem>): Completable {
        val newsItems = arrayListOf<NewsItem>()
        Log.i(TAG, newsItems.toString())
        feedItem.map { newsItems.add(NewsItem(it.link, it.title)) }
        return  Completable.fromCallable { dataBase.newsItemDao().insertNewItems(newsItems) }
    }

    override fun getBookmark(url: String): Single<WebViewItem> {
        val bookmarkedPage = dataBase.bookmarkedPageDao().getBookmarkedPage(url)
        val webViewItem =WebViewItem(bookmarkedPage.link, bookmarkedPage.html ?:"Пустая страница!")
        return Single.fromCallable{webViewItem}
    }


}