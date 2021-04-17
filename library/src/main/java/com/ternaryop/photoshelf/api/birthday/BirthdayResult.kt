package com.ternaryop.photoshelf.api.birthday

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable
import java.util.Calendar

@JsonClass(generateAdapter = true)
data class NameResult(
    @Json(name = "birthday") val birthday: Birthday,
    @Json(name = "isNew") val isNew: Boolean
)

@JsonClass(generateAdapter = true)
data class ListResult(@Json(name = "names") val names: List<String>)

@JsonClass(generateAdapter = true)
data class BirthdayResult(
    @Json(name = "total") val total: Long = 0,
    @Json(name = "birthdays") val birthdays: List<Birthday>? = null
) : Serializable

@JsonClass(generateAdapter = true)
data class Birthday(
    @Json(name = "name") val name: String,
    @Json(name = "birthdate") var birthdate: Calendar,
    @Json(name = "images") val images: List<ImageSize>? = null,
    @Json(name = "source") val source: String? = null
) : Serializable

@JsonClass(generateAdapter = true)
data class ImageSize(
    @Json(name = "width") val width: Int,
    @Json(name = "height") val height: Int,
    @Json(name = "url") val url: String
) : Serializable

// some images don't have the exact (==) width so we get closest width (<=)
fun Birthday.getClosestPhotoByWidth(width: Int):
        ImageSize? = images?.firstOrNull { it.width <= width }

class FindParams(
    val name: String? = null,
    val month: Int = -1,
    val dayOfMonth: Int = -1,
    var offset: Int = 0,
    val limit: Int = 1000,
    val onlyTotal: Boolean = false,
    val pickImages: Boolean = false,
    val blogName: String? = null
) {

    init {
        if (pickImages) {
            requireNotNull(blogName) { "blogName is mandatory with pickImages" }
        }
    }

    fun toQueryMap(): Map<String, String> {
        val map = mutableMapOf(
            "offset" to offset.toString(),
            "limit" to limit.toString(),
            "onlyTotal" to onlyTotal.toString()
        )

        name?.let { map.put("name", it) }
        if (month > 0) {
            map["month"] = month.toString()
        }
        if (dayOfMonth > 0) {
            map["dayOfMonth"] = dayOfMonth.toString()
        }
        if (pickImages) {
            map["pickImages"] = "true"
            map["blogName"] = blogName!!
        }
        return map
    }
}
