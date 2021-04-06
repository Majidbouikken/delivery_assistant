package com.example.deliveryassistant.utils

import java.util.*

object DateParser {
    fun dateToString(days: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, days)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH) + 1
        val year = calendar.get(Calendar.YEAR)
        return (year.toString() + '-' + month.toString().padStart(2, '0') + '-' + day.toString()
            .padStart(2, '0'))
    }

    fun dateToLong(date: String): Long = date.replace("-", "").substring(0,8).toLong()
}