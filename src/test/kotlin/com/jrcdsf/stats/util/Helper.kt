package com.jrcdsf.stats.util

import com.jrcdsf.stats.domain.entities.Event
import java.time.Instant

object Helper {

    fun generateEventList(matching: Int, nonMatching: Int): List<Event> {
        val list: MutableList<Event> = mutableListOf()
        repeat(matching) {
            val now = Instant.ofEpochMilli(System.currentTimeMillis() - (Math.random() * 30000).toLong())
            val x = Math.random()
            val y = (1073741823..Int.MAX_VALUE).random()
            list.add(Event(now, x, y))
        }
        repeat(nonMatching) {
            val now = Instant.ofEpochMilli(System.currentTimeMillis() - ((Math.random() * 60000) + 60000).toLong())
            val x = Math.random()
            val y = (1073741823..Int.MAX_VALUE).random()
            list.add(Event(now, x, y))
        }
        return list
    }

    fun generateEventsBody(matching: Int, nonMatching: Int): String {
        var body: String = ""
        repeat(matching) {
            val now = System.currentTimeMillis() - (Math.random() * 30000).toLong()
            val x = Math.random()
            val y = (1073741823..Int.MAX_VALUE).random()
            body += ("$now,$x,$y\n")
        }
        repeat(nonMatching) {
            val now = System.currentTimeMillis() - ((Math.random() * 60000) + 60000).toLong()
            val x = Math.random()
            val y = (1073741823..Int.MAX_VALUE).random()
            body += "$now,$x,$y\n"
        }
        return body
    }
}