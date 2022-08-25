package com.jrcdsf.stats.domain.entities

import java.time.Instant

data class Event(
    val timestamp: Instant,
    val x: Double,
    val y: Int
)
