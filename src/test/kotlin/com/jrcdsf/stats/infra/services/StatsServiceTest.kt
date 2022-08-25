package com.jrcdsf.stats.infra.services

import com.jrcdsf.stats.infra.repositories.EventRepository
import com.jrcdsf.stats.infra.util.StatsCalculation
import com.jrcdsf.stats.util.Helper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class StatsServiceTest {

    lateinit var eventRepository: EventRepository

    lateinit var eventService: EventService

    lateinit var statsService: StatsService

    @BeforeEach
    fun init() {
        eventRepository = EventRepository()
        eventService = EventService(eventRepository)
        statsService = StatsService(eventService)
    }

    @Test
    fun `generateStats of an empty event list should return EMPTY`() {
        eventService.save(Helper.generateEventList(0, 5))

        assert(statsService.generateStats() == "EMPTY")
    }

    @Test
    fun `generateStats of a valid event list should return a valid statistic`() {
        val eventList = Helper.generateEventList(5, 0)
        eventService.save(eventList)

        val actual = statsService.generateStats()
        assert(actual != "EMPTY")
        assertEquals(StatsCalculation.calculateStats(eventList), actual)
    }
}