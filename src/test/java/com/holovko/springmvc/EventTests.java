package com.holovko.springmvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.holovko.springmvc.dto.event.RequestUpdateEventDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource("/application-test-h2.properties")
@Sql(value = "/import-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class EventTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getEventsList() throws Exception {
        this.mockMvc.perform(get("/events"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[*]", hasSize(3)))
                .andExpect(jsonPath("$.[0].name", is("Football")))
                .andExpect(jsonPath("$.[0].amount", is(100)))
                .andExpect(jsonPath("$.[0].price", is(1257)))
                .andExpect(jsonPath("$.[0].startDate", is("2022-01-13 00:00:00")))
                .andExpect(jsonPath("$.[2].name", is("Opera")))
                .andExpect(jsonPath("$.[2].amount", is(70)))
                .andExpect(jsonPath("$.[2].price", is(2000)))
                .andExpect(jsonPath("$.[2].startDate", is("2022-05-01 00:00:00")));
    }

    @Test
    void getEvent() throws Exception {
        long eventId = 3L;
        this.mockMvc
                .perform(get("/events/"+eventId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value("3"))
                .andExpect(jsonPath("$.name").value("Opera"))
                .andExpect(jsonPath("$.amount").value("70"))
                .andExpect(jsonPath("$.price").value("2000"))
                .andExpect(jsonPath("$.startDate").value("2022-05-01 00:00:00"));
    }

    @Test
    void getEventByBuyer() throws Exception {
        this.mockMvc
                .perform(get("/events/buyer/Peter")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value("Peter"))
                .andExpect(jsonPath("$.events[0].num").value("1"))
                .andExpect(jsonPath("$.events[0].name").value("Opera"));
    }

    @Test
    void getEventByStartDate() throws Exception {
        this.mockMvc
                .perform(get("/events/by-start-date")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[0].num").value("1"))
                .andExpect(jsonPath("$.[0].name").value("Concert"))
                .andExpect(jsonPath("$.[1].num").value("2"))
                .andExpect(jsonPath("$.[1].name").value("Football"))
                .andExpect(jsonPath("$.[2].num").value("3"))
                .andExpect(jsonPath("$.[2].name").value("Opera"));
    }

    @Test
    void getEventByNotExistingId() throws Exception {
        long eventId = 999L;
        this.mockMvc
                .perform(get("/events/"+eventId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is4xxClientError());
    }

    @Test
    void createEvent() throws Exception {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDateForTest = LocalDateTime.now();

        RequestUpdateEventDTO event = new RequestUpdateEventDTO();
        event.setName("Test Event");
        event.setPrice(2000);
        event.setAmount(10);
        event.setStartDate(startDateForTest);

        this.mockMvc
                .perform(post("/events")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(event))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value("Test Event"))
                .andExpect(jsonPath("$.amount").value("10"))
                .andExpect(jsonPath("$.price").value("2000"))
                .andExpect(jsonPath("$.startDate").value(startDateForTest.format(dateTimeFormatter)))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andReturn();

    }

    @Test
    void updateEvent() throws Exception {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDateForTest = LocalDateTime.now();

        RequestUpdateEventDTO event = new RequestUpdateEventDTO();
        event.setName("Test Event");
        event.setPrice(2000);
        event.setAmount(10);
        event.setStartDate(startDateForTest);

        this.mockMvc
                .perform(put("/events/2")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(event))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value("Test Event"))
                .andExpect(jsonPath("$.amount").value("10"))
                .andExpect(jsonPath("$.price").value("2000"))
                .andExpect(jsonPath("$.startDate").value(startDateForTest.format(dateTimeFormatter)))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andReturn();

    }

}