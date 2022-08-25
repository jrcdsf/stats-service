package com.jrcdsf.stats.infra.util

import com.jrcdsf.stats.domain.entities.Event
import java.time.Instant

object Converters {

    fun convertBodyToEvents(body: String): List<Event> {

        val lines = body.split("\r?\n|\r".toRegex()).toTypedArray()
        val eventList = lines.flatMap {
            try {
                val (timestamp, x, y) = it.split(",")
                listOf(Event(timestamp = Instant.ofEpochMilli(timestamp.toLong()), x = x.toDouble(), y = y.toInt()))
            } catch (e: Exception) {
                emptyList()
            }
        }
        return eventList
    }
}