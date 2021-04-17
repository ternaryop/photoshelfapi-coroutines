package com.ternaryop.photoshelf.api.extractor

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.ternaryop.photoshelf.api.parser.TitleComponentsResult

/**
 * Created by dave on 01/04/17.
 * The mapping object used to hold the Gallery result
 */

@JsonClass(generateAdapter = true)
data class ImageGalleryResult(
    @Json(name = "gallery") val gallery: ImageGallery)

@JsonClass(generateAdapter = true)
class ImageGallery(
    @Json(name = "url") val url: String,
    @Json(name = "domain") val domain: String? = null,
    @Json(name = "title") val title: String? = null,
    @Json(name = "titleParsed") var titleParsed: TitleComponentsResult,
    @Json(name = "gallery") val imageInfoList: List<ImageInfo>
) {
    /**
     * Return a string that can be used by the title parser
     * @return the title plus domain string
     */
    val parsableTitle: String
        get() = "$title ::::: $domain"
}

@JsonClass(generateAdapter = true)
data class ImageResult(
    @Json(name = "imageUrl") val imageUrl: String)

/**
 * @param thumbnailUrl The thumbnail image url. This is present on the HTML document from which pick images
 * @param documentUrl The destination document containing the image url
 * @param imageUrl The image url present inside the destination document url. If null must be retrieved from
 * destination document
 */
@JsonClass(generateAdapter = true)
class ImageInfo(
    @Json(name = "thumbnailUrl") val thumbnailUrl: String? = null,
    @Json(name = "documentUrl") var documentUrl: String? = null,
    @Json(name = "imageUrl") var imageUrl: String? = null
)
