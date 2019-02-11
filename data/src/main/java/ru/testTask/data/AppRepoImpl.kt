package ru.testTask.data

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import ru.testTask.core.data.DataManager
import ru.testTask.core.data.repo.FetchDataRepo
import ru.testTask.core.data.repo.LoadDataRepo
import ru.testTask.data.model.Item
import ru.testTask.data.model.Rss
import ru.testTask.data.remote.api.FeedApi
import ru.testTask.model.FeedItem
import javax.inject.Inject

class AppRepoImpl @Inject constructor(
    private val feedApi: FeedApi,
    private val dataManager: DataManager
) : FetchDataRepo, LoadDataRepo {

    override fun isFirstAppUseUse(): Single<Boolean> {
        return Single.fromCallable { dataManager.isFirstConnection() }
    }

    /**
     * @return data from api
     */
    override fun fetchFromApi(): Flowable<List<FeedItem>> {
        return feedApi
            .getRssFeed()
            .flatMap { rss: Rss -> mapApiResultToAppModel(rss.mChannel?.mItems) }
    }

    /**
     * @return the cached data from local storage
     */
    override fun fetchDataFromDb(): Flowable<List<FeedItem>> {
        return dataManager.getFeedItems()
    }


    //TODO: Отдельный маппер(class)
    private fun mapApiResultToAppModel(items: List<Item>?): Flowable<List<FeedItem>> {
        val feedItem = arrayListOf<FeedItem>()
        items?.map { item -> feedItem.add(FeedItem(item.mTitle!!, item.mLink!!)) }
        addItemToTheDb(feedItem)
        return  Flowable.fromCallable{feedItem}

    }

    private fun addItemToTheDb(items: List<FeedItem>): Completable {
       return dataManager.addItemstoTheDb(items)
    }

}