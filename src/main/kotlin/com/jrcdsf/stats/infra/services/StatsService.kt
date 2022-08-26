package com.jrcdsf.stats.infra.services

import com.jrcdsf.stats.domain.entities.Event
import com.jrcdsf.stats.infra.util.StatsCalculation
import org.springframework.stereotype.Service

@Service
class StatsService(private val eventService: EventService) {

    private lateinit var events: List<Event>

    fun generateStats(): String {
        getStats()
        return StatsCalculation.calculateStats(events)
    }

    private fun getStats() {
        events = eventService.getEventsFromTheLast60Seconds()
    }


}