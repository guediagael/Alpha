package ru.testTask.data.remote.api

import io.reactivex.Flowable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET
import ru.testTask.data.BuildConfig
import ru.testTask.data.model.Rss


interface FeedApi {

    @GET("/_/rss/_rss.html?subtype=1&category=2&city=21")
    fun getRssFeed(): Flowable<Rss>

    companion object Factory {
        fun create(): FeedApi {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .build()
            return retrofit.create(FeedApi::class.java)
        }
    }
}