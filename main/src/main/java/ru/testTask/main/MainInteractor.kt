package ru.testTask.main

import io.reactivex.Flowable
import ru.testTask.core.data.repo.LoadDataRepo
import ru.testTask.core.rx.SchedulerProvider
import ru.testTask.model.FeedItem
import ru.testTask.model.WebViewItem
import javax.inject.Inject

class MainInteractor @Inject constructor(
    private val loadDataRepo: LoadDataRepo
) {
    fun loadItems():Flowable<List<FeedItem>>{
        return loadDataRepo.fetchDataFromDb()

    }

    fun loadBookMarkedItems():Flowable<List<WebViewItem>>{
        return loadDataRepo.fetchBookmarkedWebViewItems()
    }


}