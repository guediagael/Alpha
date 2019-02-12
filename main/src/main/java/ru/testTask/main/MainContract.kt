package ru.testTask.main

import android.content.Context
import ru.testTask.model.FeedItem

interface MainContract {
    interface View {
        fun feedItemsLoaded(feedItems: List<FeedItem>)
        fun onError(errorMessage: String)
    }

    interface Presenter {
        fun onCreate(view: View)
        fun showDetails(context: Context, firstElementToShowUrl: String, otherFeedElementUrls: List<String>)
    }
}