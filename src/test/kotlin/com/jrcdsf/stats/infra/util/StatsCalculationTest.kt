package com.jrcdsf.stats.infra.util

import com.jrcdsf.stats.util.Helper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class StatsCalculationTest {

    @Test
    fun `calculateStats of an empty event list should return EMPTY`() {
        assert(StatsCalculation.calculateStats(emptyList()) == "EMPTY")
    }

    @Test
    fun `calculateStats of a valid event list should return a valid statistic`() {
        val eventList = Helper.generateEventList(5, 0)
        val xList = eventList.map { it.x }
        val yList = eventList.map { it.y }
        val total = eventList.size
        val sumX = xList.sum()
        val avgX = sumX / xList.size
        val sumY = yList.sum()
        val avgY = sumY.toFloat() / yList.size
        val stat = "$total,$sumX,$avgX,$sumY,$avgY"

        assertEquals(stat, StatsCalculation.calculateStats(eventList))
    }
}