package com.ternaryop.photoshelf

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
//    @Before
//    fun before() {
//        @Before
//        fun before() {
//            val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//            ApiManager
//                .setup(AppSupport(this)
//                    .photoShelfApikey, BuildConfig.PHOTOSHELF_API_PREFIX, okHttpClient)
//
//        }
//
//    }


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        assertEquals("com.ternaryop.photoshelf.test", appContext.packageName)
    }
}
