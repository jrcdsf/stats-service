package com.jrcdsf.stats.controllers

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
internal class EventControllerTest {

    @Autowired
    lateinit var mvc: MockMvc
    private val body = """1661441426010,0.0747666061,1729343026
                        1661441457983,0.3258131742,1161486840
                        1661441480998,0.0906980783,1791770265
                        1661441479987,0.0366829336,1669266084"""

    @Test
    fun `POST to endpoint event and valid body should return 202`() {
        mvc.post("/event") {
            contentType = MediaType.TEXT_PLAIN
            content = body
        }.andExpect {
            status { isAccepted() }
        }
    }
}