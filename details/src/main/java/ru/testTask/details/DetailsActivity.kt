package ru.testTask.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import android.webkit.MimeTypeMap
import kotlinx.android.synthetic.main.activity_details.*
import ru.testTask.details.di.DetailsComponent
import ru.testTask.model.WebViewItem
import ru.testTask.ui.WebViewMotionDetector
import javax.inject.Inject

class DetailsActivity : AppCompatActivity(), DetailsContract.View, WebViewMotionDetector.SwipeDetectorListener {


    companion object {
        private const val INTENT_KEY_URL_LIST = "url_list"
        private const val INTENT_KEY_FIRST_ELEMENT = "first_element_url"
        fun start(context: Context, urlList: ArrayList<String>?, firstElementUrl: String?) {
            val intent = Intent(context, DetailsActivity::class.java)
            if (urlList?.size?.compareTo(0) != 0 && firstElementUrl?.length?.compareTo(0) != 0) {
                intent.putStringArrayListExtra(INTENT_KEY_URL_LIST, urlList)
                intent.putExtra(INTENT_KEY_FIRST_ELEMENT, firstElementUrl)
            }
            context.startActivity(intent)

        }

        private val TAG = DetailsActivity::class.java.simpleName
    }

    @Inject
    lateinit var presenter: DetailsContract.Presenter
    private lateinit var links: List<String>
    private lateinit var pages: List<WebViewItem>
    private lateinit var mGestureDetectorCompat: GestureDetectorCompat
    private lateinit var mCurrentLink: String

    override fun webViewItemLoaded(webViewItems: List<WebViewItem>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(errorMessage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBookmarkStatusChecked(isBookmarked: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        DetailsComponent.create(this).inject(this)


        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels.toFloat()
        mGestureDetectorCompat = GestureDetectorCompat(this, WebViewMotionDetector(this, width))

        webView.setOnTouchListener { v, event -> onTouchEvent(event)  }
        if (intent.hasExtra(INTENT_KEY_URL_LIST) && intent.hasExtra(INTENT_KEY_FIRST_ELEMENT)) {
//            TODO("use internet to load ")
            val myIntent = intent.extras

            val currentUrl = myIntent?.getString(INTENT_KEY_FIRST_ELEMENT)

            links = myIntent?.getStringArrayList(INTENT_KEY_URL_LIST)!!

            if (!currentUrl.isNullOrBlank()) initWebViewForOnlineUse(currentUrl)
        } else {
            presenter.loadWebViewItems(this)
        }

    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        mGestureDetectorCompat.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    private fun initWebViewForOfflineUse() {

    }

    private fun initWebViewForOnlineUse(currentPage: String) {
        mCurrentLink = currentPage
        webView.loadUrl(currentPage)

    }


    private fun getNextUrl(): String? {
        val currentIndex = links.indexOf(mCurrentLink)
        return if (currentIndex < links.size - 1) links[currentIndex + 1] else null
    }

    private fun getPrevUrl(): String? {
        val currentIndex = links.indexOf(mCurrentLink)
        return if (currentIndex > 0) links[currentIndex - 1] else null
    }

    private fun getNextHtmlData(): String? {
        val links = pages.map { it.content }
        val currentIndex = links.indexOf(mCurrentLink)
        return if (currentIndex < links.size - 1) links[currentIndex + 1] else null
    }

    private fun getPrevData(): String? {
        val links = pages.map { it.content }
        val currentIndex = links.indexOf(mCurrentLink)
        return if (currentIndex > 0) links[currentIndex - 1] else null
    }

    override fun swipeLeft() {
        Log.i(TAG,"swiping left")
        if (links.isNotEmpty()) {
            val url = getPrevUrl()
            url?.let {
                webView.loadUrl(url)
                mCurrentLink = url
            }
        }
        else {
            val html = getPrevData()
            html?.let { webView.loadDataWithBaseURL(mCurrentLink,html, "text/html", null, null) }
        }
    }

    override fun swipeRight() {
        Log.i(TAG,"swiping right")
        if (links.isNotEmpty()) {
            val url = getNextUrl()
            url?.let {
                webView.loadUrl(url)
                mCurrentLink = url
            }
        }
        else {
            val html = getNextHtmlData()
            html?.let { webView.loadDataWithBaseURL(mCurrentLink,html, "text/html", null, null) }
        }
    }

}

