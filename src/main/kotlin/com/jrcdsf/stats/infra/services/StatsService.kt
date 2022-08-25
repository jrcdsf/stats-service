package com.jrcdsf.stats.infra.services

import com.jrcdsf.stats.domain.entities.Event
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StatsService {

    @Autowired
    private lateinit var eventService: EventService

    private lateinit var events: List<Event>;

    fun generateStats(): String {
        getStats()
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

    private fun getStats() {
        events = eventService.getEventsWithinThreshold()
    }


}