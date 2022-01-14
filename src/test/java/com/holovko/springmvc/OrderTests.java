package com.holovko.springmvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.holovko.springmvc.dto.order.RequestCreateOrderDTO;
import com.holovko.springmvc.dto.order.RequestUpdateOrderDTO;
import com.holovko.springmvc.exception.EventExpiredException;
import com.holovko.springmvc.exception.EventNotFoundException;
import com.holovko.springmvc.exception.TicketsSoldOutException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Test
    void getOrder() throws Exception {
        long orderId = 1L;
        this.mockMvc
                .perform(get("/orders/"+orderId)
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
        long orderId = 999L;
        this.mockMvc
                .perform(get("/orders/"+orderId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is4xxClientError());
    }

    @Test
    void createOrder() throws Exception {
        long eventId = 1L;
        RequestCreateOrderDTO orderDTO = new RequestCreateOrderDTO();
        orderDTO.setBuyerName("Buyer");
        orderDTO.setAmount(1);
        orderDTO.setTotalPrice(100);

        this.mockMvc
                .perform(post("/orders/events/"+eventId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDTO))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.buyerName").value("Buyer"))
                .andExpect(jsonPath("$.amount").value("1"))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.event.name").value("Football"))
                .andExpect(jsonPath("$.event.amount").value("100"))
                .andReturn();
    }

    @Test
    void createOrderWithEventExpiredException() throws Exception {
        long eventId = 2L;
        RequestCreateOrderDTO orderDTO = new RequestCreateOrderDTO();
        orderDTO.setBuyerName("Test Buyer");
        orderDTO.setAmount(1);

        MvcResult result = this.mockMvc
                .perform(post("/orders/events/"+eventId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDTO))
                )
                .andExpect(status().is4xxClientError())
                .andReturn();
        Assertions.assertTrue(result.getResolvedException() instanceof EventExpiredException);
    }

    @Test
    void createOrderWithEventNotFoundException() throws Exception {
        long eventId = 999L;
        RequestCreateOrderDTO orderDTO = new RequestCreateOrderDTO();
        orderDTO.setBuyerName("Test Buyer");
        orderDTO.setAmount(1);


        MvcResult result = this.mockMvc
                .perform(post("/orders/events/"+eventId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDTO))
                )
                .andExpect(status().is4xxClientError())
                .andReturn();
        Assertions.assertTrue(result.getResolvedException() instanceof EventNotFoundException);

    }

    @Test
    void createOrderWithTicketsSoldOutException() throws Exception {
        long eventId = 1L;
        RequestCreateOrderDTO orderDTO = new RequestCreateOrderDTO();
        orderDTO.setBuyerName("Test Buyer");
        orderDTO.setAmount(250);


        MvcResult result = this.mockMvc
                .perform(post("/orders/events/"+eventId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDTO))
                )
                .andExpect(status().is4xxClientError())
                .andReturn();
        Assertions.assertTrue(result.getResolvedException() instanceof TicketsSoldOutException);

    }

    @Test
    void updateOrder() throws Exception {
        long orderId = 1L;
        long eventId = 3L;
        RequestUpdateOrderDTO orderDTO = new RequestUpdateOrderDTO();
        orderDTO.setBuyerName("Test Buyer");
        orderDTO.setAmount(1);

        this.mockMvc
                .perform(put("/orders/"+orderId+"/events/"+eventId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDTO))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.buyerName").value("Test Buyer"))
                .andExpect(jsonPath("$.amount").value("1"))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.event.name").value("Opera"))
                .andExpect(jsonPath("$.event.amount").value("70"))
                .andReturn();
    }

    @Test
    void updateOrderWithEventExpiredException() throws Exception {
        long orderId = 2L;
        long eventId = 2L;
        RequestUpdateOrderDTO orderDTO = new RequestUpdateOrderDTO();
        orderDTO.setBuyerName("Test Buyer");
        orderDTO.setAmount(1);

        MvcResult result = this.mockMvc
                .perform(put("/orders/"+orderId+"/events/"+eventId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDTO))
                )
                .andExpect(status().is4xxClientError())
                .andReturn();
        Assertions.assertTrue(result.getResolvedException() instanceof EventExpiredException);
    }

    @Test
    void updateOrderWithEventNotFoundException() throws Exception {
        long orderId = 1L;
        long eventId = 999L;
        RequestUpdateOrderDTO orderDTO = new RequestUpdateOrderDTO();
        orderDTO.setBuyerName("Test Buyer");
        orderDTO.setAmount(1);


        MvcResult result = this.mockMvc
                .perform(put("/orders/"+orderId+"/events/"+eventId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDTO))
                )
                .andExpect(status().is4xxClientError())
                .andReturn();
        Assertions.assertTrue(result.getResolvedException() instanceof EventNotFoundException);

    }

    @Test
    void updateOrderWithTicketsSoldOutException() throws Exception {
        long orderId = 1L;
        long eventId = 3L;
        RequestCreateOrderDTO orderDTO = new RequestCreateOrderDTO();
        orderDTO.setBuyerName("Test Buyer");
        orderDTO.setAmount(250);


        MvcResult result = this.mockMvc
                .perform(put("/orders/"+orderId+"/events/"+eventId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDTO))
                )
                .andExpect(status().is4xxClientError())
                .andReturn();
        Assertions.assertTrue(result.getResolvedException() instanceof TicketsSoldOutException);

    }

}
