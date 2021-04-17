package com.ternaryop.photoshelf.api.post

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Locale

@JsonClass(generateAdapter = true)
data class LatestTimestampResult(
    @Json(name = "importCount") val importCount: Int,
    @Json(name = "lastPublishTimestamp") val lastPublishTimestamp: Long,
    @Json(name = "publishedIdList") val publishedIdList: List<String>?)

@JsonClass(generateAdapter = true)
data class LastPublishedTag(
    @Json(name = "tag") val tag: String,
    @Json(name = "titleId") val titleId: String,
    @Json(name = "publishTimestamp") val publishTimestamp: Long
)

@JsonClass(generateAdapter = true)
data class LastPublishedTitleHolder(
    @Json(name = "titles") val titles: Collection<LastPublishedTitle>
)

@JsonClass(generateAdapter = true)
data class LastPublishedTitle(
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String
)

@JsonClass(generateAdapter = true)
data class LatestTagResult(
    @Json(name = "tags") val tags: List<LastPublishedTag>
) {
    fun findByTag(tag: String) = tags.firstOrNull { it.tag == tag }
}

@JsonClass(generateAdapter = true)
data class StatsResult(
    @Json(name = "stats") val stats: Map<String, Long>
)

@JsonClass(generateAdapter = true)
data class MisspelledResult(
    @Json(name = "misspelled") val misspelled: String,
    @Json(name = "corrected") val corrected: String?
)

@JsonClass(generateAdapter = true)
data class TagInfo(val tag: String, var postCount: Long) {
    fun compareTagTo(other: TagInfo): Int = tag.compareTo(other.tag, ignoreCase = true)
}

@JsonClass(generateAdapter = true)
data class TagInfoListResult(val pattern: String, val tags: List<TagInfo>)

fun List<String>.toTagInfo(): List<TagInfo> {
    return groupingBy { it.toLowerCase(Locale.getDefault()) }
        .fold(
            { key, _ -> TagInfo(key, 0) },
            { _, tagInfo, _ -> tagInfo.also { ++it.postCount } })
        .values.toList()
}
