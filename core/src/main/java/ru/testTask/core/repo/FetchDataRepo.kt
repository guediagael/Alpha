package ru.testTask.core.repo

import io.reactivex.Flowable
import io.reactivex.Single
import ru.testTask.model.FeedItem

interface FetchDataRepo {
    fun fetchFromApi(): Flowable<FeedItem>
    fun isFirstAppUseUse() : Single<Boolean>

}