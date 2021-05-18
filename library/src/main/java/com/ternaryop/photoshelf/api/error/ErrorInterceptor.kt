package com.ternaryop.photoshelf.api.error

import com.squareup.moshi.Moshi
import okhttp3.Interceptor

internal val errorInterceptor = Interceptor { chain ->
    val request = chain.request()
    val response = chain.proceed(request)

    if (!response.isSuccessful) {
        response.body?.source()?.also { source ->
            source.request(Long.MAX_VALUE) // Buffer the entire body.
            val json = source.buffer.clone().readUtf8()
            Moshi.Builder()
                .build()
                .adapter(ErrorInfo::class.java)
                .fromJson(json)?.also {
                    throw PhotoShelfApiException(it)
                }
        }
    }
    response
}