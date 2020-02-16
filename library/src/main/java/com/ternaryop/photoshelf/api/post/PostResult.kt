package com.ternaryop.photoshelf.api.post

import java.util.Locale

class LatestTimestampResult(
    val importCount: Int,
    val lastPublishTimestamp: Long,
    val publishedIdList: List<String>?
)

class LastPublishedTag(
    val tag: String,
    val titleId: String,
    val publishTimestamp: Long
)

class LastPublishedTitleHolder(
    val titles: Collection<LastPublishedTitle>
)

class LastPublishedTitle(
    val id: String,
    val title: String
)

class LatestTagResult(val tags: List<LastPublishedTag>) {
    fun findByTag(tag: String) = tags.firstOrNull { it.tag == tag }
}

class StatsResult(val stats: Map<String, Long>)

class MisspelledResult(val misspelled: String, val corrected: String?)

class TagInfo(val tag: String, var postCount: Long) {
    fun compareTagTo(other: TagInfo): Int = tag.compareTo(other.tag, ignoreCase = true)
}

class TagInfoListResult(val pattern: String, val tags: List<TagInfo>)

fun List<String>.toTagInfo(): List<TagInfo> {
    return groupingBy { it.toLowerCase(Locale.getDefault()) }
        .fold(
            { key, _ -> TagInfo(key, 0) },
            { _, tagInfo, _ -> tagInfo.also { ++it.postCount } })
        .values.toList()
}
