package ru.testTask.core.data.repo

import io.reactivex.Flowable
import io.reactivex.Observable
import ru.testTask.model.FeedItem

interface LoadDataRepo {
    fun fetchDataFromDb(): Flowable<List<FeedItem>>
}