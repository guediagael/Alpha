package ru.testTask.core.repo

import io.reactivex.Observable
import ru.testTask.model.FeedItem

interface LoadDataRepo {
    fun fetchDataFromDb(): Observable<FeedItem>
}