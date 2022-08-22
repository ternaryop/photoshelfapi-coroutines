package com.ternaryop.photoshelf.api.moshi.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

class CalendarAdapter {
    @FromJson
    fun fromJson(isoDate: String): Calendar =
        DateTimeFormatter.ISO_LOCAL_DATE.parse(isoDate, LocalDate::from).let { localDate ->
            Calendar.getInstance().apply {
                clear()
                set(Calendar.YEAR, localDate.year)
                set(Calendar.DAY_OF_MONTH, localDate.dayOfMonth)
                set(Calendar.MONTH, localDate.monthValue - 1)
            }
        }

    @ToJson
    fun toJson(calendar: Calendar): String = DateTimeFormatter.ISO_LOCAL_DATE.format(calendar.toInstant())
}
