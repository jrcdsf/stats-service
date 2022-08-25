package com.jrcdsf.stats.infra.repositories

import com.jrcdsf.stats.util.Helper
import org.junit.jupiter.api.Assertions.assertIterableEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class EventRepositoryTest {

    private lateinit var eventRepository: EventRepository

    @BeforeEach
    fun setUp() {
        eventRepository = EventRepository()
    }

    @Test
    fun `saving a list of events should return true`() {
        assert(eventRepository.save(Helper.generateEventList(3, 1)))
    }

    @Test
    fun `getAllEvents should return a list of all stored events`() {
        val expected = Helper.generateEventList(3, 1)

        val saved = eventRepository.save(expected)
        val actual = eventRepository.getAllEvents()

        assert(saved)
        assertIterableEquals(expected, actual)
    }
}