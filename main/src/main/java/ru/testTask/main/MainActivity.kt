package ru.testTask.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import ru.testTask.common.utils.showShortToast
import ru.testTask.main.di.MainComponent
import ru.testTask.model.FeedItem
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainContract.View{

    companion object {
        fun start(context: Context){
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    @Inject lateinit var presenter: MainContract.Presenter
    @Inject lateinit var recyclerViewAdapter: MainRecyclerViewAdapter
    @Inject lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MainComponent.create(this).inject(this)
        //get an illegalStateException when I use android extension
//        recyclerView = findViewById(R.id.recyclerViewNewsFeed)
        recyclerViewNewsFeed?.layoutManager = layoutManager
        recyclerViewNewsFeed?.adapter = recyclerViewAdapter
        presenter.onCreate(this)

    }

    override fun feedItemsLoaded(feedItems: List<FeedItem>) {
        Log.i("feedItmeSize: ", feedItems.size.toString())
        recyclerViewAdapter.addItems(feedItems)
    }

    override fun onError(errorMessage: String) {
        showShortToast(errorMessage)
    }
}
