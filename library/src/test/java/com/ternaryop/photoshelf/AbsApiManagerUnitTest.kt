package com.ternaryop.photoshelf

import com.ternaryop.photoshelf.api.ApiManager
import org.junit.Before
import java.text.SimpleDateFormat
import java.util.Properties

abstract class AbsApiManagerUnitTest {
    lateinit var blogName: String
    lateinit var properties: Properties

    val sdf = SimpleDateFormat("yyyy-MM-dd")

    @Before
    fun before() {
        properties = Properties()
        javaClass.classLoader!!.getResourceAsStream("config.properties")
            .use { properties.load(it) }

        blogName = properties.getProperty("blogName")
        ApiManager
            .setup(properties.getProperty("apikey"), properties.getProperty("apiPrefix"), null)
    }
}