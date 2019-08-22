package com.ternaryop.photoshelf.api.birthday

import com.ternaryop.photoshelf.api.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * Created by dave on 01/04/17.
 * Birthday API Manager
 * Migrated to Retrofit API Service on 08/08/2018.
 */

interface BirthdayService {
    @GET("v1/birthday/name/get")
    suspend fun getByName(@Query("name") name: String, @Query("searchIfNew") searchIfNew: Boolean)
        : Response<NameResult>

    @GET("v1/birthday/date/find")
    suspend fun findByDate(@QueryMap params: Map<String, String>): Response<BirthdayResult>

    @GET("v1/birthday/date/sameday")
    suspend fun findSameDay(@QueryMap params: Map<String, String>): Response<BirthdayResult>

    @GET("v1/birthday/date/ignored")
    suspend fun findIgnored(@QueryMap params: Map<String, String>): Response<ListResult>

    @GET("v1/birthday/date/orphans")
    suspend fun findOrphans(@QueryMap params: Map<String, String>): Response<BirthdayResult>

    @GET("v1/birthday/name/missing")
    suspend fun findMissingNames(@QueryMap params: Map<String, String>): Response<ListResult>

    @DELETE("v1/birthday/name")
    suspend fun deleteByName(@Query("name") name: String)

    @PUT("v1/birthday/date/edit")
    suspend fun updateByName(@Query("name") name: String, @Query("birthdate") isoBirthdate: String)

    @PUT("v1/birthday/name/ignore")
    suspend fun markAsIgnored(@Query("name") name: String)

    companion object {
        const val MAX_BIRTHDAY_COUNT = 200
    }
}
