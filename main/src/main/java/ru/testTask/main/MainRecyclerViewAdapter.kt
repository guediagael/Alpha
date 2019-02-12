package ru.testTask.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_holder_feed_item.view.*
import ru.testTask.model.FeedItem

class MainRecyclerViewAdapter(private var items: MutableList<FeedItem>) : RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>(){
    private lateinit var listener: MainAdapterListener

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int):ViewHolder {
        return LayoutInflater.from(p0.context).inflate(R.layout.view_holder_feed_item, p0,false) as ViewHolder
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(items.get(p1), {url-> run { listener.itemSelected(url) } })
    }

    fun addItems(feedItems : List<FeedItem>){
        items.clear()
        items.addAll(feedItems)
        notifyDataSetChanged()
    }

    fun addItem(feedItem: FeedItem){
        items.add(feedItem)
        notifyDataSetChanged()
    }



    interface MainAdapterListener{
        fun itemSelected(url: String)
    }

    class ViewHolder (itemView : View): RecyclerView.ViewHolder( itemView){

        fun bind(feedItem: FeedItem, listener: (String)->Unit) = with (feedItem){
            itemView.textViewFeedItemTitle.text = feedItem.title
            itemView.setOnClickListener { listener(feedItem.link) }
        }
    }


}