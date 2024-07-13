package pl.amiloh.spring_tests.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
class ProductControllerIT {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired // autoconfigured by @AutoConfigureMockMvc
    MockMvc mockMvc;

    /* second way to configure mockMvc
    private MockMvc mockMvc;
    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }
     */

    @AfterEach
    void tearDown() {
        // clear repositories
    }

    @Test
    void getAll_ReturnValidResponseEntity() throws Exception {
        // given
        var requestBuilder = MockMvcRequestBuilders.get("/products");

        // when
        mockMvc.perform(requestBuilder)
            // then
            .andExpectAll(
                status().isOk(),
//                header().exists("...")
                content().contentType(MediaType.APPLICATION_JSON)
//                content().json(""),
//                jsonPath("$.id").exists()
            );
    }
}