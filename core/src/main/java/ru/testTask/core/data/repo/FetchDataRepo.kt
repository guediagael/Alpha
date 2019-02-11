package ru.testTask.core.data.repo

import io.reactivex.Flowable
import io.reactivex.Single
import ru.testTask.model.FeedItem

interface FetchDataRepo {
    fun fetchFromApi(): Flowable<List<FeedItem>>
    fun isFirstAppUseUse() : Single<Boolean>

}