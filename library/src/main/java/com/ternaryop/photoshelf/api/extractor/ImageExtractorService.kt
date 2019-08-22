package com.ternaryop.photoshelf.api.extractor

import com.ternaryop.photoshelf.api.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by dave on 01/04/17.
 * Image Extractor Manager
 * Migrated to Retrofit API Service on 08/08/2018.
 */

interface ImageExtractorService {
    @GET("v1/extract/gallery")
    suspend fun getGallery(@Query("url") url: String): Response<ImageGalleryResult>

    @GET("v1/extract/image")
    suspend fun getImageUrl(@Query("url") url: String): Response<ImageResult>
}
