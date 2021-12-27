package com.holovko.springmvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource("/application-test-h2.properties")
@Sql(value = "/import-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(value = "/clean-test.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class SpringmvcApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void defaultPageRedirectToSwagger() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(redirectedUrl("swagger-ui/index.html?configUrl=/api-docs/swagger-config"));
    }
}
