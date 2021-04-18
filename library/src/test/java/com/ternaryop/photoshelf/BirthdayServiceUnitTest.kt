package com.ternaryop.photoshelf

import com.ternaryop.photoshelf.api.ApiManager
import com.ternaryop.photoshelf.api.birthday.FindParams
import kotlinx.coroutines.runBlocking
import org.junit.Test

class BirthdayServiceUnitTest : AbsApiManagerUnitTest() {
    @Test
    fun findByDate() {
        runBlocking {
            val response = ApiManager.birthdayService().findByDate(FindParams(
                month = 11,
                dayOfMonth = 14,
                blogName = blogName).toQueryMap())
            response.response.birthdays?.forEach {
                val birthdate = it.birthdate?.let { sdf.format(it.time) }
                println("BirthdayUnitTest.findByDate ${it.name} ${birthdate}")
            }
        }
    }

    @Test
    fun findMissingNames() {
        runBlocking {
            val response = ApiManager.birthdayService().findIgnored(FindParams(
                name = "a",
                blogName = blogName).toQueryMap())
            println("BirthdayUnitTest.findIgnored ${response.response.names}")
        }
    }

    @Test
    fun getByName() {
        runBlocking {
            val response = ApiManager.birthdayService().getByName("not existing", true).response
            println("BirthdayUnitTest.findIgnored ${response}")
        }
    }
}