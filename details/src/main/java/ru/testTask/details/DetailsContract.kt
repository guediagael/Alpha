package ru.testTask.details

import ru.testTask.model.WebViewItem

interface DetailsContract {
    interface View{
        fun webViewItemLoaded(webViewItems: List<WebViewItem>)
        fun onError(errorMessage: String)
        fun onBookmarkStatusChecked(isBookmarked: Boolean)
    }

    interface Presenter{
        fun loadWebViewItems(view: View)
        fun bookmarkPage(webViewItem: WebViewItem)
        fun checkBookmarkStatus(url: String)
    }
}