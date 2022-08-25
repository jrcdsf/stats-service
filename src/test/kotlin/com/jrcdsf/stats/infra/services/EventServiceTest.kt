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
    fun `saving a list of events should return true if the list is saved`() {
        assertEquals(true, eventService.save(Helper.generateEventList(3, 1)))
    }

    @Test
    fun `getEventsWithinThreshold for a list within the threshold should return a list of events matching threshold`() {

        val saved = eventService.save(Helper.generateEventList(5, 1))

        val actual = eventService.getEventsWithinThreshold()

        assert(saved)
        assert(actual.size == 5)
    }

    @Test
    fun `getEventsWithinThreshold for a list outside the threshold should return an empty list`() {

        val saved = eventService.save(Helper.generateEventList(0, 5))

        val actual = eventService.getEventsWithinThreshold()

        assert(saved)
        assert(actual.isEmpty())
    }
}