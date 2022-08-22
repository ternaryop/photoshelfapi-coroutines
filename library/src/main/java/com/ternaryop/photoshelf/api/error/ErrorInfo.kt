package com.ternaryop.photoshelf.api.error

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ErrorInfo(val status: String, val errorMessage: String)
