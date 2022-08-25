package com.jrcdsf.stats.infra.repositories

import co.touchlab.stately.isolate.IsolateState
import com.jrcdsf.stats.domain.entities.Event
import org.springframework.stereotype.Repository

@Repository
class EventRepository {

    private val storedEvents = IsolateState { mutableSetOf<Event>() }
    fun save(eventList: List<Event>): Boolean = storedEvents.access { it.addAll(eventList) }

    fun getAllEvents(): MutableSet<Event> = storedEvents.access { it }

}
