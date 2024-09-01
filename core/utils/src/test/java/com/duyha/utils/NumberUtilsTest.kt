package com.duyha.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * @author: DuyHa
 * @date: 01/09/2024
 */
class NumberUtilsTest {
    @Test
    fun roundNumber_Zero_ShouldReturnZero() {
        val result = NumberUtils.roundNumber(0)
        assertThat(result).isEqualTo("0")
    }

    @Test
    fun roundNumber_LessThanTen_ShouldReturnNumber() {
        val result = NumberUtils.roundNumber(5)
        assertThat(result).isEqualTo("5")
    }

    @Test
    fun roundNumber_Ten_ShouldReturnNumber() {
        val result = NumberUtils.roundNumber(10)
        assertThat(result).isEqualTo("10")
    }

    @Test
    fun roundNumber_LessThanOneHundred_ShouldReturnRoundedNumber() {
        val result = NumberUtils.roundNumber(99)
        assertThat(result).isEqualTo("10+")
    }

    @Test
    fun roundNumber_Hundred_ShouldReturnHundred() {
        val result = NumberUtils.roundNumber(200)
        assert(result == "100+")
    }

    @Test
    fun roundNumber_GreaterThanHundred_ShouldReturnNumber() {
        val result = NumberUtils.roundNumber(200)
        assert(result == "100+")
    }
}