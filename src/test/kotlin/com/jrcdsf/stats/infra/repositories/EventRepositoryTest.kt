package com.jrcdsf.stats.infra.repositories

import com.jrcdsf.stats.util.Helper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class EventRepositoryTest {

    private lateinit var eventRepository: EventRepository

    @BeforeEach
    fun setUp() {
        eventRepository = EventRepository()
    }

    @Test
    fun `saving a list of events should return the number of events saved`() {
        assertEquals(4, eventRepository.save(Helper.generateEventList(3, 1)))
    }

    @Test
    fun `getBucket should return a list of all stored events`() {
        val expected = Helper.generateEventList(1, 0)

        val saved = eventRepository.save(expected)
        val actual = eventRepository.getBucket(expected[0].timestamp.toString().substring(0, 19))

        assertEquals(1, saved)
        assertEquals(expected, actual)
    }
}