package com.duyha.utils

/**
 * @author: DuyHa
 * @date: 01/09/2024
 */
object NumberUtils {
    fun roundNumber(number: Int): String {
        return when {
            number in 11..99 -> "10+"
            number > 100 -> "100+"
            else -> number.toString()
        }
    }
}