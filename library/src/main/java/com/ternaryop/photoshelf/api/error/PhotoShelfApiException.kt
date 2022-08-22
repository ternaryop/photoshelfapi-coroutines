package com.ternaryop.photoshelf.api.error

import java.io.IOException

class PhotoShelfApiException(val errorInfo: ErrorInfo) :
    IOException(errorInfo.errorMessage)
