package com.holovko.springmvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.holovko.springmvc.dto.order.RequestCreateOrderDTO;
import com.holovko.springmvc.repository.EventRepository;
import com.holovko.springmvc.repository.OrderRepository;
import com.holovko.springmvc.service.OrderService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
/*
    @Autowired
    OrderService orderService;

    @MockBean
    EventRepository eventRepository;

    @MockBean
    OrderRepository orderRepository;

 */

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

    @Test
    void getOrder() throws Exception {
        this.mockMvc
                .perform(get("/orders/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.buyerName").value("Peter"))
                .andExpect(jsonPath("$.amount").value("1"))
                .andExpect(jsonPath("$.totalPrice").value("2000"));
    }

    @Test
    void getOrderByNotExistingId() throws Exception {
        this.mockMvc
                .perform(get("/orders/999")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is4xxClientError());
    }

    @Test
    void createOrder() throws Exception {
        long eventId = 1L;
        RequestCreateOrderDTO orderDTO = new RequestCreateOrderDTO();
        orderDTO.setBuyerName("Test Buyer");
        orderDTO.setAmount(1);

        this.mockMvc
                .perform(post("/orders/events/"+eventId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDTO))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.buyerName").value("Test Buyer"))
                .andExpect(jsonPath("$.amount").value("1"))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.event.name").value("Football"))
                .andExpect(jsonPath("$.event.amount").value("100"))
                .andReturn();
    }


}
