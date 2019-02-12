package ru.testTask.main

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_holder_feed_item.view.*
import ru.testTask.model.FeedItem

class MainRecyclerViewAdapter(private var items: MutableList<FeedItem>) :
    RecyclerView.Adapter<MainRecyclerViewAdapter.MainAdapterViewHolder>() {
    companion object {
        val TAG = MainRecyclerViewAdapter::class.java.simpleName
    }

    private lateinit var listener: (String) -> Unit

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MainAdapterViewHolder {
        val cardView = LayoutInflater.from(p0.context).inflate(R.layout.view_holder_feed_item, p0, false)
        return MainAdapterViewHolder(cardView)
    }

    fun setListener(listener: (String) -> Unit) {
        this.listener = listener
    }


    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(p0: MainAdapterViewHolder, p1: Int) {

        p0.bind(items[p1]) { url -> run { listener(url) } }
    }

    fun addItems(feedItems: List<FeedItem>) {
        Log.d("$TAG adding items:", feedItems.size.toString())
        items.clear()
        items.addAll(feedItems)
        notifyDataSetChanged()
    }

    fun addItem(feedItem: FeedItem) {
        items.add(feedItem)
        notifyDataSetChanged()
    }


    fun getItemUrls():List<String>{
        return items.map { it.link }
    }

    class MainAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(feedItem: FeedItem, listener: (String) -> Unit) = with(feedItem) {
            itemView.textViewFeedItemTitle.text = feedItem.title
            itemView.setOnClickListener { listener(feedItem.link) }
        }
    }


}