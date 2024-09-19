package pl.amilosh.spring_01.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.amilosh.spring_01.model.Task;
import pl.amilosh.spring_01.repository.InMemTaskRepository;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public class TasksRestControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    InMemTaskRepository inMemTaskRepository;

    @AfterEach
    void tearDown() {
        inMemTaskRepository.getTasks().clear();
    }

    @Test
    void testGetAllTasks_ReturnsValidResponseEntity() throws Exception {
        // given
        var requestBuilder = get("/api/tasks");
        inMemTaskRepository.getTasks().addAll(List.of(
            new Task(UUID.fromString("71117396-8694-11ed-9ef6-77042ee83937"), "First task", false),
            new Task(UUID.fromString("7172d834-8694-11ed-8669-d7b17d45fba8"), "Second task", true)
        ));

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
    void handleCreateNewTask_PayloadIsValid_ReturnsValidResponseEntity() throws Exception {
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

        assertEquals(1, inMemTaskRepository.getTasks().size());
        assertEquals("Third task", inMemTaskRepository.getTasks().get(0).details());
    }

    @Test
    void handleCreateNewTask_PayloadIsInvalid_ReturnsValidResponseEntity() throws Exception {
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
