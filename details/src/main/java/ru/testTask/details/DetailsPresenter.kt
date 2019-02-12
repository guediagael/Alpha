package ru.testTask.details

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bookmarkPage(webViewItem: WebViewItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun checkBookmarkStatus(url: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}