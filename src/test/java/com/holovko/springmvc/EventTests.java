package com.holovko.springmvc;

import com.holovko.springmvc.controller.EventController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource("/application-test.properties")
@Sql(value = "/import-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/clean-test.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class EventTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getEventsList() throws Exception {
        this.mockMvc.perform(get("/events"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/hal+json"))
            .andExpect(jsonPath("$[*]", hasSize(2)))
            .andExpect(jsonPath("$._embedded.events[0].name", is("Football")))
            .andExpect(jsonPath("$._embedded.events[0].amount", is(100)))
            .andExpect(jsonPath("$._embedded.events[0].price", is(1257)))
            .andExpect(jsonPath("$._embedded.events[0].startDate", is("2022-01-13 00:00:00")))
            .andExpect(jsonPath("$._embedded.events[2].name", is("Opera")))
            .andExpect(jsonPath("$._embedded.events[2].amount", is(70)))
            .andExpect(jsonPath("$._embedded.events[2].price", is(2000)))
            .andExpect(jsonPath("$._embedded.events[2].startDate", is("2022-05-01 00:00:00")));
    }

    @Test
    void getEvent() throws Exception {
        this.mockMvc.perform(get("/events/3"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id", is(3)));
    }
}

