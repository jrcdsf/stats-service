package com.jrcdsf.stats.infra.config

import com.jrcdsf.stats.domain.entities.Event
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.CopyOnWriteArrayList

@Configuration
class EventConfig {

    @Bean
    fun getCopyOnWriteArrayList(): CopyOnWriteArrayList<Event> {
        return CopyOnWriteArrayList<Event>()
    }
}