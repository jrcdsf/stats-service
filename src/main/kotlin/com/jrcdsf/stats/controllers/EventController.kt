package com.jrcdsf.stats.controllers

import com.jrcdsf.stats.domain.entities.Event
import com.jrcdsf.stats.infra.services.EventService
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
class EventController {

    @Autowired
    private lateinit var eventService: EventService

    private val logger = KotlinLogging.logger {}

    @PostMapping("/event", consumes = ["text/plain;charset=UTF-8", "text/csv;charset=UTF-8"])
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun handleEvents(@RequestBody events: String) {
        val eventList = convertBodyToEvents(events)
        eventService.save(eventList)
        logger.debug { eventList }
    }

    private fun convertBodyToEvents(body: String): MutableList<Event> {
        val eventList: MutableList<Event> = mutableListOf()

        val lines = body.split("\r?\n|\r".toRegex()).toTypedArray()
        lines.iterator().forEach { line ->
            val (timestamp, x, y) = line.split(",")
            eventList.add(Event(timestamp = Instant.ofEpochMilli(timestamp.toLong()), x = x.toDouble(), y = y.toInt()))
        }

        return eventList

    }
}