package com.nutricare.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    private const val DATE_FORMAT = "yyyy-MM-dd"

    fun getTodayDate(): String {
        return SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(Date())
    }

    fun formatDate(timestamp: Long): String {
        return SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(Date(timestamp))
    }

    fun parseDate(dateString: String): Date? {
        return try {
            SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).parse(dateString)
        } catch (e: Exception) {
            null
        }
    }

    fun getWeekStart(date: Date = Date()): String {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        return SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(calendar.time)
    }

    fun getWeekEnd(date: Date = Date()): String {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        calendar.add(Calendar.DATE, 1)
        return SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(calendar.time)
    }
}
