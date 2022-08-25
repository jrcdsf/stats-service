package com.jrcdsf.stats.infra.services

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.Instant

internal class EventServiceTest {

    lateinit var eventService: EventService

    @BeforeEach
    fun init() {
        eventService = EventService()
    }

    @Test
    fun save() {
    }

    @Test
    fun getEventsWithinThreshold() {
        val threshold: Int = 60000
        val nowTimestamp = "1661349697469"
        val now = Instant.ofEpochMilli(nowTimestamp.toLong())
        val nowMinusThreshold = Instant.ofEpochMilli(nowTimestamp.toLong() - threshold)

        val events = listOf(
            "1661349681486",
            "1661349649474",
            "1661349648474",
            "1661349702474",
            "1661349648465"
        ).map { Instant.ofEpochMilli(it.toLong()) }
        val filtered = events.filter { event -> now.isAfter(event) && nowMinusThreshold.isBefore(event) }
        println(filtered)

        eventService.getEventsWithinThreshold()

    }
}