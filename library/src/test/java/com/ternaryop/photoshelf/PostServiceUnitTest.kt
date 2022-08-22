package com.ternaryop.photoshelf

import com.ternaryop.photoshelf.api.ApiManager
import kotlinx.coroutines.runBlocking
import org.junit.Test

class PostServiceUnitTest: AbsApiManagerUnitTest() {
    @Test
    fun stats() {
        runBlocking {
            val response = ApiManager.postService().getStats(blogName).response
            println("Post.test $response")
        }
    }
}
