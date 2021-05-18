package com.ternaryop.photoshelf.api

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.ternaryop.photoshelf.api.error.errorInterceptor
import com.ternaryop.photoshelf.api.moshi.adapter.CalendarAdapter
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@JsonClass(generateAdapter = true)
class Response<T>(val response: T)

private const val WAIT_TIMEOUT_MINUTES = 5L

class PhotoShelfApi(
    private val accessToken: String,
    private val apiUrlPrefix: String,
    private val okHttpClient: OkHttpClient?
) {
    inline fun <reified T> service(): T = builder.create(T::class.java)

    val builder: Retrofit by lazy {
        val moshi = Moshi.Builder()
            .add(CalendarAdapter())
            .build()
        val interceptor = Interceptor { chain: Interceptor.Chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("PhotoShelf-Subscription-Key", accessToken).build()
            chain.proceed(newRequest)
        }

        val builder = okHttpClient?.newBuilder() ?: OkHttpClient.Builder()
        builder.interceptors().add(interceptor)
        builder.interceptors().add(errorInterceptor)
        builder.readTimeout(WAIT_TIMEOUT_MINUTES, TimeUnit.MINUTES)

        Retrofit.Builder()
            .baseUrl(apiUrlPrefix)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(builder.build())
            .build()
    }
}
