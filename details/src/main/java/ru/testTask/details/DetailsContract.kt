package ru.testTask.details

import ru.testTask.model.WebViewItem

interface DetailsContract {
    interface View{
        fun webViewItemLoaded(webViewItems: List<WebViewItem>)
        fun onError(errorMessage: String)
        fun onBookmarkStatusChecked(isBookmarked: Boolean)
        fun pageBookmarked()
    }

    interface Presenter{
        fun loadWebViewItems(view: View)
        fun bookmarkPage(url: String)
        fun checkBookmarkStatus(url: String)

    }
}