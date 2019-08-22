package com.ternaryop.photoshelf.api.parser

import com.ternaryop.photoshelf.api.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ParserService {
    @GET("v1/parser/components")
    suspend fun components(
        @Query("title") title: String,
        @Query("swapDayMonth") swapDayMonth: Boolean = false,
        @Query("checkDateInTheFuture") checkDateInTheFuture: Boolean = true): Response<TitleComponentsResult>
}