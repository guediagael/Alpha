package ru.testTask.details

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_details.*
import ru.testTask.details.di.DetailsComponent
import ru.testTask.model.WebViewItem
import javax.inject.Inject

class DetailsActivity : AppCompatActivity(), DetailsContract.View {

    companion object {
        private const val INTENT_KEY_URL_LIST= "url_list"
        private const val INTENT_KEY_FIRST_ELEMENT = "first_element_url"
        fun  start(context: Context, urlList: ArrayList<String>?, firstElementUrl: String?){
            val intent = Intent(context, DetailsActivity::class.java)
            if (urlList?.size?.compareTo(0) != 0 && firstElementUrl?.length?.compareTo(0) !=0){
                intent.putStringArrayListExtra(INTENT_KEY_URL_LIST, urlList)
                intent.putExtra(INTENT_KEY_FIRST_ELEMENT,firstElementUrl)
            }
            context.startActivity(intent)

        }

        private  val TAG = DetailsActivity::class.java.simpleName
    }

    @Inject lateinit var presenter: DetailsContract.Presenter

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
        if (intent.hasExtra(INTENT_KEY_URL_LIST) && intent.hasExtra(INTENT_KEY_FIRST_ELEMENT)){
//            TODO("use internet to load ")
            val myIntent = intent.extras

            val firstElementUrl  =myIntent?.get(INTENT_KEY_FIRST_ELEMENT)
            val firstElementUrl2  =myIntent?.getString(INTENT_KEY_FIRST_ELEMENT)

            Log.d(TAG, firstElementUrl2)
            Log.d(TAG, firstElementUrl.toString())
            initWebViewForOnlineUse(intent.getStringExtra(INTENT_KEY_FIRST_ELEMENT))
        }else{
            presenter.loadWebViewItems(this)
        }

    }

    private fun initWebViewForOfflineUse(){

    }

    private fun initWebViewForOnlineUse(currentPage: String){
        webView.loadUrl(currentPage)
    }
}
