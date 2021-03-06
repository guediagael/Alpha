package ru.testTask.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import ru.testTask.data.BuildConfig


class ApiClient {

    companion object apiClient {
        val retrofitClient = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(OkHttpClient())
        .addConverterFactory(SimpleXmlConverterFactory.create()).build()

    }
}