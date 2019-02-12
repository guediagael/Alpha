package ru.testTask.details

import io.reactivex.Completable
import io.reactivex.Flowable
import ru.testTask.core.data.repo.WebViewRepo
import ru.testTask.model.WebViewItem
import javax.inject.Inject

class DetailsInteractor @Inject constructor(
    private val webViewRepo: WebViewRepo
) {
    fun savePageToDb(webViewItemUrl: String):Completable{
        return webViewRepo.bookMarkPage(webViewItemUrl)
    }

    fun loadBookmarkedPages():Flowable<List<WebViewItem>>{
        return webViewRepo.loadBookmarkedWebItems()
    }
}