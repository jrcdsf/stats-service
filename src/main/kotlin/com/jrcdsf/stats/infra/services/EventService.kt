package com.jrcdsf.stats.infra.services

import co.touchlab.stately.isolate.IsolateState
import com.jrcdsf.stats.domain.entities.Event
import com.jrcdsf.stats.infra.repositories.EventRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class EventService {

    @Autowired
    private lateinit var eventRepository: EventRepository

    fun save(events: List<Event>): Boolean {
        return eventRepository.save(events)
    }

    fun getEventsWithinThreshold(threshold: Int = 60000): List<Event> {
        val now = Instant.ofEpochMilli(System.currentTimeMillis())
        val nowMinusThreshold = Instant.ofEpochMilli(System.currentTimeMillis() - threshold)
        val events = IsolateState { eventRepository.getAllEvents() }
        return events.access { it.filter { event -> now.isAfter(event.timestamp) && nowMinusThreshold.isBefore(event.timestamp) } }

    }
}