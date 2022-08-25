package com.jrcdsf.stats.infra.util

import com.jrcdsf.stats.domain.entities.Event

object StatsCalculation {

    fun calculateStats(events: List<Event>): String {
        return if (events.isNotEmpty()) {
            val xList = events.map { it.x }
            val yList = events.map { it.y }
            val total = events.size
            val sumX = xList.sum()
            val avgX = sumX / xList.size
            val sumY = yList.sum()
            val avgY = sumY.toFloat() / yList.size

            "$total,$sumX,$avgX,$sumY,$avgY"
        } else "EMPTY"
    }
}