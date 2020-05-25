package com.news.shared.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    val dateformat = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    /**
     * Get Date object from string
     * @param str_date the string which we want to convert in DATE format
     */
    fun getDateFormat(str_date: String): Date? {
        val format = SimpleDateFormat(dateformat)
        var date: Date? = null
        try {
            date = format.parse(str_date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date
    }

}
