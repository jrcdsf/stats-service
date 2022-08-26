package com.jrcdsf.stats.infra.repositories

import co.touchlab.stately.isolate.IsolateState
import com.jrcdsf.stats.domain.entities.Event
import org.springframework.stereotype.Repository

@Repository
class EventRepository {

    private val bucket: IsolateState<MutableMap<String, MutableList<Event>>> = IsolateState { mutableMapOf() }
    fun save(eventList: List<Event>): Int {
        var counter = 0
        for (event in eventList) {
            val key = event.timestamp.toString().substring(0, 19)
            bucket.access {
                if (it[key] == null)
                    it[key] = mutableListOf()
                it[key]?.add(event)
            }
            counter++
        }
        return counter
    }

    fun getBucket(hashKey: String): MutableList<Event>? {
        return bucket.access { it[hashKey] }
    }
}
