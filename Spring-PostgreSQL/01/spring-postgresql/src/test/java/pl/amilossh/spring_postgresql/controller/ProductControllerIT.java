package pl.amilossh.spring_postgresql.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("/sql/product_controller/test_data.sql")
@Transactional
@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
class ProductControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAll() throws Exception {
        // given
        var requestBuilder = get("/products");

        // when
        this.mockMvc.perform(requestBuilder)
            // then
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_JSON),
                content().json("""
                    [
                        {
                            "id": "71117396-8694-11ed-9ef6-77042ee83937",
                            "name": "Sony"
                        },
                        {
                            "id": "7172d834-8694-11ed-8669-d7b17d45fba8",
                            "details": "Apple"
                        }
                    ]
                    """)
            );

    }
}