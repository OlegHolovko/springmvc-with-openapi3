package com.holovko.springmvc;

import com.holovko.springmvc.controller.EventController;
import org.junit.Test;
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
@WebMvcTest(EventController.class)
public class EventTests{
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getList() throws Exception {
        //this.mockMvc.perform(get("/events")).andExpect(status().isOk());
        this.mockMvc.perform(get("/"))
                .andExpect(redirectedUrl("swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config"));
        //this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk());
    }
}
