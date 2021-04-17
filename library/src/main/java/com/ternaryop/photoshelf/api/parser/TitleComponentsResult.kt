package com.ternaryop.photoshelf.api.parser

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventDate(
    @Json(name = "day") val day: Int?,
    @Json(name = "month") val month: Int?,
    @Json(name = "year") val year: Int?)

@JsonClass(generateAdapter = true)
data class TitleComponentsResult(
    @Json(name = "who") val who: List<String>,
    @Json(name = "tags") val tags: List<String>,
    @Json(name = "html") val html: String,
    @Json(name = "location") val location: String?,
    @Json(name = "city") val city: String?,
    @Json(name = "eventDate") val eventDate: EventDate?
)
