package com.ternaryop.photoshelf.api

import com.google.gson.GsonBuilder
import com.ternaryop.photoshelf.api.util.gson.CalendarDeserializer
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class Response<T>(val response: T)

class PhotoShelfApi(private val accessToken: String,
    private val apiUrlPrefix: String,
    private val okHttpClient: OkHttpClient?) {
    inline fun <reified T> service() : T = builder.create(T::class.java)

    val builder: Retrofit by lazy {
        val gson = GsonBuilder()
            .registerTypeAdapter(Calendar::class.java, CalendarDeserializer())
            .create()
        val interceptor = Interceptor {
            chain: Interceptor.Chain -> {
            val newRequest = chain.request().newBuilder()
                .addHeader("PhotoShelf-Subscription-Key", accessToken).build()
            chain.proceed(newRequest)
            }()
        }

        val builder = okHttpClient?.newBuilder() ?: OkHttpClient.Builder()
        builder.interceptors().add(interceptor)
        builder.readTimeout(5, TimeUnit.MINUTES)

        Retrofit.Builder()
            .baseUrl(apiUrlPrefix)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(builder.build())
            .build()
    }
}
