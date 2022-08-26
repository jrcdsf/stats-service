package com.jrcdsf.stats.infra.services

import com.jrcdsf.stats.domain.entities.Event
import com.jrcdsf.stats.infra.repositories.EventRepository
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class EventService(private val eventRepository: EventRepository) {

    fun save(events: List<Event>): Int {
        return eventRepository.save(events)
    }

    fun getEventsWithinThreshold(threshold: Int = 60000): List<Event> {
        val bucketList = mutableListOf<Event>()
        val now = Instant.ofEpochMilli(System.currentTimeMillis())

        for (sec in 60 downTo 1) {
            val bucket = eventRepository.getBucket(now.minusSeconds(sec.toLong()).toString().substring(0, 19))
            if (bucket != null) {
                bucketList.addAll(bucket)
            }
        }
        return bucketList
    }
}