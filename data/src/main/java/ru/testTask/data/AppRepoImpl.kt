package ru.testTask.data

import android.util.Log
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.jsoup.Connection
import org.jsoup.Jsoup
import ru.testTask.core.data.DataManager
import ru.testTask.core.data.repo.FetchDataRepo
import ru.testTask.core.data.repo.LoadDataRepo
import ru.testTask.core.data.repo.WebViewRepo
import ru.testTask.core.rx.SchedulerProvider
import ru.testTask.data.model.Item
import ru.testTask.data.model.Rss
import ru.testTask.data.remote.api.FeedApi
import ru.testTask.model.FeedItem
import ru.testTask.model.WebViewItem
import javax.inject.Inject

class AppRepoImpl @Inject constructor(
    private val feedApi: FeedApi,
    private val dataManager: DataManager,
    private val schedulerProvider: SchedulerProvider
) : FetchDataRepo, LoadDataRepo, WebViewRepo {

    companion object {
        val TAG = AppRepoImpl::class.java.simpleName
    }

    override fun setIsNotTheFirstUse() {
        dataManager.setIfNotFirstConnection()
    }

    override fun isFirstAppUseUse(): Single<Boolean> {
        return Single.fromCallable { dataManager.isFirstConnection() }
    }

    /**
     * @return data from api
     */
    override fun fetchFromApi(): Flowable<List<FeedItem>> {

        return feedApi.getRssFeed()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .flatMap { rss: Rss -> mapApiResultToAppModel(rss.mChannel?.mItems) }.doOnNext { feedItems ->
                run {
                    addItemToTheDb(feedItems)
                }
            }


    }

    /**
     * @return the cached data from local storage
     */
    override fun fetchDataFromDb(): Flowable<List<FeedItem>> {
        return dataManager.getFeedItems().observeOn(schedulerProvider.ui()).subscribeOn(schedulerProvider.io())
    }

    override fun bookMarkPage(webViewItemUrl: String): Completable {

        return Completable.fromSingle(getHtml(webViewItemUrl)
            .doOnSuccess { html ->
                run {
                    val webViewItem = WebViewItem(webViewItemUrl, html)
                    dataManager.bookmarkPage(webViewItem).subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui()).subscribe()
                }


            }).doOnError { t -> Log.e(TAG, t.message, t) }


    }

    override fun unbookmarkPage(webViewItem: WebViewItem): Completable {
        return dataManager.removeBookmark(webViewItem).subscribeOn(schedulerProvider.ui())
            .observeOn(schedulerProvider.io())
    }

    override fun loadBookmarkedWebItems(): Flowable<List<WebViewItem>> {
        return dataManager.getBookMarks().subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui())
    }


    //TODO: Отдельный маппер(class)
    private fun mapApiResultToAppModel(items: List<Item>?): Flowable<List<FeedItem>> {
        val feedItems = arrayListOf<FeedItem>()
        items?.map { item -> feedItems.add(FeedItem(item.mTitle!!, item.mLink!!)) }
//        addItemToTheDb(feedItems)

        return Flowable.fromCallable { feedItems }
    }

    private fun addItemToTheDb(items: List<FeedItem>): Completable {
        return dataManager.addItemstoTheDb(items)
    }


    private fun getHtml(url: String): Single<String> {
        val connection = Jsoup.connect(url).method(Connection.Method.GET)
        return Single.fromCallable {
            connection.execute()
        }.subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.io())
            .flatMap { t -> Single.fromCallable { t.body() } }.doOnSuccess { t -> Log.d(TAG, t) }
            .doOnError { t -> Log.e(TAG, t.message, t) }
    }

    override fun fetchBookmarkedWebViewItems(): Flowable<List<WebViewItem>> {
        return loadBookmarkedWebItems()
    }
}