package com.jrcdsf.stats.infra.services

import com.jrcdsf.stats.infra.repositories.EventRepository
import com.jrcdsf.stats.util.Helper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class EventServiceTest {

    lateinit var eventRepository: EventRepository

    lateinit var eventService: EventService

    @BeforeEach
    fun init() {
        eventRepository = EventRepository()
        eventService = EventService(eventRepository)
    }

    @Test
    fun `saving a list of events should return the number of events saved`() {
        assertEquals(4, eventService.save(Helper.generateEventList(3, 1)))
    }

    @Test
    fun `getEventsFromTheLast60Seconds should return a list of events from the last 60 secs window`() {

        val saved = eventService.save(Helper.generateEventList(5, 1))

        val actual = eventService.getEventsFromTheLast60Seconds()

        assertEquals(6, saved)
        assert(actual.size == 5)
    }

    @Test
    fun `getEventsFromTheLast60Seconds should return an empty list if there are no events in the last 60 secs window`() {

        val saved = eventService.save(Helper.generateEventList(0, 5))

        val actual = eventService.getEventsFromTheLast60Seconds()

        assertEquals(5, saved)
        assert(actual.isEmpty())
    }
}