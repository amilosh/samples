package pl.amilosh.spring_01.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql("/sql/tasks_rest_controller/test_data.sql")
@Transactional
@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public class TasksRestControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testGetAllTasks_ReturnsValidResponseEntity() throws Exception {
        // given
        var requestBuilder = get("/api/tasks");

        // when
        mockMvc.perform(requestBuilder)
            // then
            .andExpectAll(
                status().isOk(),
                content().contentType(MediaType.APPLICATION_JSON),
                content().json("""
                    [
                        {
                            "id": "71117396-8694-11ed-9ef6-77042ee83937",
                            "details": "First task",
                            "completed": false
                        },
                        {
                            "id": "7172d834-8694-11ed-8669-d7b17d45fba8",
                            "details": "Second task",
                            "completed": true
                        }
                    ]
                    """)
            );
    }

    @Test
    void testCreateNewTask_PayloadIsValid_ReturnsValidResponseEntity() throws Exception {
        // given
        var requestBuilder = post("/api/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {
                    "details": "Third task"
                }
                """);

        // when
        mockMvc.perform(requestBuilder)
            // then
            .andExpectAll(
                status().isCreated(),
                header().exists(HttpHeaders.LOCATION),
                content().contentType(MediaType.APPLICATION_JSON),
                content().json("""
                    {
                        "details": "Third task",
                        "completed": false
                    }
                    """),
                jsonPath("$.id").exists()
            );
    }

    @Test
    void testCreateNewTask_PayloadIsInvalid_ReturnsValidResponseEntity() throws Exception {
        // given
        var requestBuilder = post("/api/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.ACCEPT_LANGUAGE, "en")
            .content("""
                {
                    "details": null
                }
                """);

        // when
        mockMvc.perform(requestBuilder)
            // then
            .andExpectAll(
                status().isBadRequest(),
                header().doesNotExist(HttpHeaders.LOCATION),
                content().contentType(MediaType.APPLICATION_JSON),
                content().json("""
                    {
                        "errors": ["Task details must be set"]
                    }
                    """, true)
            );
    }
}
