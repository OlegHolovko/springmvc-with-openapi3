package com.holovko.springmvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource("/application-test-h2.properties")
@Sql(value = "/import-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class OrderTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getOrdersList() throws Exception {
        this.mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[*]", hasSize(4)))
                .andExpect(jsonPath("$.[0].buyerName", is("Peter")))
                .andExpect(jsonPath("$.[0].amount", is(1)))
                .andExpect(jsonPath("$.[0].totalPrice", is(2000)))
                .andExpect(jsonPath("$.[1].buyerName", is("John")))
                .andExpect(jsonPath("$.[1].amount", is(2)))
                .andExpect(jsonPath("$.[1].totalPrice", is(4000)));
    }
}
