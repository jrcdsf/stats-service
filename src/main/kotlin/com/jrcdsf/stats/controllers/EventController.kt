package com.jrcdsf.stats.controllers

import com.jrcdsf.stats.infra.services.EventService
import com.jrcdsf.stats.infra.util.Converters
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class EventController(private val eventService: EventService) {

    @PostMapping("/event", consumes = ["text/plain;charset=UTF-8", "text/csv;charset=UTF-8"])
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun handleEvents(@RequestBody events: String) {
        val eventList = Converters.convertBodyToEvents(events)
        eventService.save(eventList)
    }
}