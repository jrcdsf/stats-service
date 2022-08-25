package com.jrcdsf.stats.controllers

import com.jrcdsf.stats.infra.services.StatsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class StatsController(private val statsService: StatsService) {

    @GetMapping("/stats")
    @ResponseStatus(HttpStatus.OK)
    fun getStats(): ResponseEntity<String> {
        val response = statsService.generateStats()
        if (response != "EMPTY")
            return ResponseEntity(response, HttpStatus.OK)
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }
}