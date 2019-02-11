package ru.testTask.data

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Single
import ru.testTask.core.repo.FetchDataRepo
import ru.testTask.core.repo.LoadDataRepo
import ru.testTask.data.model.Item
import ru.testTask.data.model.Rss
import ru.testTask.data.remote.api.FeedApi
import ru.testTask.model.FeedItem
import java.util.concurrent.Callable
import javax.inject.Inject

class AppRepoImpl  (private val feedApi: FeedApi): FetchDataRepo, LoadDataRepo {


    override fun isFirstAppUseUse(): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * @return data from api
     */
    override fun fetchFromApi(): Flowable<FeedItem> {
        return feedApi.getRssFeed().flatMap { rss: Rss-> mapApiResultToAppModel(rss.mChannel?.mItems) }
    }

    /**
     * @return the cached data from local storage
     */
    override fun fetchDataFromDb(): Observable<FeedItem> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private fun mapApiResultToAppModel(items: List<Item>?): Flowable<FeedItem>{
        return Flowable.fromIterable(items?.map { item-> FeedItem(item.mTitle!!, item.mLink!!) })
    }
}