package com.jrcdsf.stats.infra.util

import com.jrcdsf.stats.domain.entities.Event
import com.jrcdsf.stats.util.Helper
import org.junit.jupiter.api.Assertions.assertIterableEquals
import org.junit.jupiter.api.Test
import java.time.Instant

internal class ConvertersTest {

    @Test
    fun `convertBodyToEvents should convert a string body into a list of events`() {

        val body = Helper.generateEventsBody(3, 1)
        val lines = body.split("\r?\n|\r".toRegex()).toTypedArray()
        val eventList = lines.flatMap {
            try {
                val (timestamp, x, y) = it.split(",")
                listOf(Event(timestamp = Instant.ofEpochMilli(timestamp.toLong()), x = x.toDouble(), y = y.toInt()))
            } catch (e: Exception) {
                emptyList()
            }
        }

        assertIterableEquals(eventList, Converters.convertBodyToEvents(body))
    }
}