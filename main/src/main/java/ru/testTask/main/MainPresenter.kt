package ru.testTask.main

import android.content.Context
import android.util.Log
import ru.testTask.core.rx.SchedulerProvider
import ru.testTask.model.FeedItem

class MainPresenter(
    private val mainRouter: MainRouter,
    private val schedulerProvider: SchedulerProvider,
    private val mainInteractor: MainInteractor
) : MainContract.Presenter {
    companion object {
        val TAG = MainPresenter::class.java.simpleName
    }
    lateinit var view: MainContract.View
    override fun onCreate(view : MainContract.View) {
        this.view = view
        Log.d("getting items", "...")
        mainInteractor.loadItems()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({items-> kotlin.run{
                Log.d(TAG, items.toString())
                view.feedItemsLoaded(items)
            }}, {t: Throwable? ->  kotlin.run{
                Log.e(TAG,t?.message, t)
                view.onError("Ощибка")
            } })


    }

    override fun showDetails(context: Context, firstElementToShowUrl: String) {
        mainRouter.openDetails(context, firstElementToShowUrl)
    }
}