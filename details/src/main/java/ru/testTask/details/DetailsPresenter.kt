package ru.testTask.details

import android.util.Log
import ru.testTask.core.rx.SchedulerProvider
import ru.testTask.model.WebViewItem

class DetailsPresenter(
    private val schedulerProvider: SchedulerProvider,
    private val detailsInteractor: DetailsInteractor
): DetailsContract.Presenter {

    companion object {
        private  val TAG = DetailsPresenter::class.java.simpleName
    }

    private lateinit var view: DetailsContract.View
    override fun loadWebViewItems(view: DetailsContract.View) {
        detailsInteractor.loadBookmarkedPages()
            .subscribeOn(schedulerProvider.io())
            .subscribeOn(schedulerProvider.ui())
            .subscribe({items->view.webViewItemLoaded(items)},{t-> Log.e(TAG, t.message, t)})
    }

    override fun bookmarkPage(webViewItemUrl: String) {
        detailsInteractor.savePageToDb(webViewItemUrl)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({ {view.pageBookmarked()}},{view.onError("somethig wrong happende")})
    }

    override fun checkBookmarkStatus(url: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}