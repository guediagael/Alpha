package ru.testTask.core.data.repo

import io.reactivex.Completable
import io.reactivex.Flowable
import ru.testTask.model.WebViewItem

interface WebViewRepo {

    fun bookMarkPage(webViewItemUrl: String): Completable
    fun unbookmarkPage(webViewItem: WebViewItem): Completable
    fun loadBookmarkedWebItems(): Flowable<List<WebViewItem>>
}