package com.jrcdsf.stats.controllers

import com.jrcdsf.stats.infra.util.StatsCalculation
import com.jrcdsf.stats.util.Helper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import javax.transaction.Transactional

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Transactional
internal class StatsControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    private val body = Helper.generateEventsBody(3, 0)

    @Test
    fun `GET to endpoint stats without saving events should return 404`() {
        mvc.get("/stats").andExpect {
            status { isNotFound() }
        }
    }

    @Test
    fun `GET to endpoint stats with valid events already saved should return 200`() {
        val expected = StatsCalculation.calculateStats(body)
        mvc.post("/event") {
            contentType = MediaType.TEXT_PLAIN
            content = body
        }.andDo {
            mvc.get("/stats").andExpect {
                status { isOk() }
                content {
                    contentTypeCompatibleWith("text/plain")
                    string(expected)
                }
            }
        }
    }
}