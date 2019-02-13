package ru.testTask.main

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import ru.testTask.common.utils.showShortToast
import ru.testTask.main.di.MainComponent
import ru.testTask.model.FeedItem
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainContract.View {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var presenter: MainContract.Presenter
    @Inject
    lateinit var recyclerViewAdapter: MainRecyclerViewAdapter
    @Inject
    lateinit var layoutManager: LinearLayoutManager
    private var mIsOffline: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainComponent.create(this).inject(this)
        recyclerViewNewsFeed?.layoutManager = layoutManager
        recyclerViewNewsFeed?.adapter = recyclerViewAdapter
        initView()
        recyclerViewAdapter.setListener { url: String ->
            presenter.showDetails(this, url, recyclerViewAdapter.getItemUrls())
        }

    }


    override fun feedItemsLoaded(feedItems: List<FeedItem>) {
        if(swipeToRefresh.isRefreshing) swipeToRefresh.isRefreshing = false

        showShortToast(if (mIsOffline) "из закладок" else "из сервера")

        recyclerViewAdapter.addItems(feedItems)
    }

    override fun onError(errorMessage: String) {

        showShortToast(errorMessage)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (mIsOffline) {
            val inflater = menuInflater
            inflater.inflate(R.menu.menu, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_switchToSaved -> {
                reloadElements()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initView() {
        mIsOffline = checkNetworkStatus()
        presenter.onCreate(this, mIsOffline)
        swipeToRefresh.setOnRefreshListener {
            run {
                presenter.onCreate(this, mIsOffline)

            }
        }
    }

    private fun reloadElements() {
        mIsOffline =!mIsOffline
        if (!mIsOffline){
            if (checkNetworkStatus()){
                presenter.onCreate(this,false)
            }else {
                mIsOffline = true
                presenter.onCreate(this, true)
            }
        }else presenter.onCreate(this, true)
    }




    private fun checkNetworkStatus():Boolean{
        return (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
            .activeNetworkInfo?.isConnected == true
    }


}
