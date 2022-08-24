package com.jrcdsf.stats.infra.repositories

import com.jrcdsf.stats.domain.entities.Event
import org.springframework.stereotype.Repository

import java.util.concurrent.CopyOnWriteArrayList

@Repository
class EventRepository(private var storedEvents: CopyOnWriteArrayList<Event>) {
    fun save(eventList: List<Event>): Boolean {
        val result = storedEvents.addAll(eventList)
        return result
    }

    fun getAllEvents(): List<Event> {
        return storedEvents
    }
}
