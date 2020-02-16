package com.ternaryop.photoshelf.api.post

import com.ternaryop.photoshelf.api.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by dave on 14/06/2018.
 * Photo Post API Manager
 * Migrated to Retrofit API Service on 08/08/2018.
 */
interface PostService {
    @GET("v1/post/{blogName}/latestTimestamp")
    suspend fun getLastPublishedTimestamp(
        @Path("blogName") blogName: String,
        @Query("since") since: Long
    ): Response<LatestTimestampResult>

    @GET("v1/post/{blogName}/stats")
    suspend fun getStats(@Path("blogName") blogName: String): Response<StatsResult>

    @GET("v1/post/tag")
    suspend fun getCorrectMisspelledName(@Query("misspelled") name: String): Response<MisspelledResult>

    @POST("v1/post/{blogName}/latestTag")
    suspend fun getLastPublishedTag(
        @Path("blogName") blogName: String,
        @Body titles: LastPublishedTitleHolder
    ): Response<LatestTagResult>

    @GET("v1/post/{blogName}/tags")
    suspend fun findTags(
        @Path("blogName") blogName: String,
        @Query("t") pattern: String
    ): Response<TagInfoListResult>

    @FormUrlEncoded
    @POST("v1/post/editTags")
    suspend fun editTags(@Field("postId") postId: Long, @Field("t[]") tags: List<String>)

    @DELETE("v1/post/{postId}")
    suspend fun deletePost(@Path("postId") postId: Long)
}
