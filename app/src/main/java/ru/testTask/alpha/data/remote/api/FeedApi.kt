package ru.testTask.alpha.data.remote.api

import io.reactivex.Observable
import retrofit2.http.GET
import ru.testTask.alpha.data.model.Rss

interface FeedApi {

    @GET("/_/rss/_rss.html?subtype=1&category=2&city=21")
    fun getRssFeed(): Observable<Rss>
}