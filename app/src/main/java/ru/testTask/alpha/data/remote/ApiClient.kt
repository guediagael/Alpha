package ru.testTask.alpha.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import ru.testTask.alpha.BuildConfig
import ru.testTask.alpha.data.remote.api.FeedApi

class ApiClient {
    object apiClient {
        val retofitInstance =
            Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(OkHttpClient())
                .addConverterFactory(SimpleXmlConverterFactory.create()).build()
    }


    object feedApi {
        val mFeedApi : FeedApi  = apiClient.retofitInstance.create(FeedApi::class.java)
    }
}