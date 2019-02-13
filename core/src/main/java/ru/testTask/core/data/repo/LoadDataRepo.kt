package ru.testTask.core.data.repo

import io.reactivex.Flowable
import io.reactivex.Observable
import ru.testTask.model.FeedItem
import ru.testTask.model.WebViewItem

interface LoadDataRepo {
    fun fetchDataFromDb(): Flowable<List<FeedItem>>
    fun fetchBookmarkedWebViewItems():Flowable<List<WebViewItem>>
}