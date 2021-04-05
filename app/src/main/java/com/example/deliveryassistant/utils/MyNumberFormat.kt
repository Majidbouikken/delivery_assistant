package com.example.deliveryassistant.utils

import java.text.NumberFormat
import java.util.*

object MyNumberFormat {
    fun phoneNumberFormat(phone_number: String): String {
        return ("+" + phone_number.substring(0, 3) + " " + phone_number.substring(
            3,
            6
        ) + " " + phone_number.substring(6, 8) + " " + phone_number.substring(
            8,
            10
        ) + " " + phone_number.substring(10, 12))
    }

    fun thousandSeparator(num: Long): String = NumberFormat.getInstance(Locale.FRANCE).format(num)
}