package pl.amilosh.spring_01.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;
import pl.amilosh.spring_01.dto.NewTaskPayload;
import pl.amilosh.spring_01.exception.ErrorsPresentation;
import pl.amilosh.spring_01.model.Task;
import pl.amilosh.spring_01.repository.TaskRepository;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class TasksRestControllerTest {

    @Mock
    TaskRepository taskRepository;

    @Mock
    MessageSource messageSource;

    @InjectMocks
    TasksRestController tasksRestController;

    @Test
    @DisplayName("GET /api/tasks returns HTTP-response with status 200 OK and with list of tasks")
    void testGetAllTasks_ReturnsValidResponseEntity() {
        // given
        var tasks = List.of(
            new Task(UUID.randomUUID(), "First task", false),
            new Task(UUID.randomUUID(), "Second task", true));
        doReturn(tasks).when(taskRepository).findAll();

        // when
        var responseEntity = tasksRestController.getAllTasks();

        // then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        assertEquals(tasks, responseEntity.getBody());
    }

    @Test
    void testCreateNewTask_PayloadIsValid_ReturnsValidResponseEntity() {
        // given
        var details = "Third task";

        // when
        var responseEntity = tasksRestController.createNewTask(new NewTaskPayload(details),
            UriComponentsBuilder.fromUriString("http://localhost:8080"), Locale.ENGLISH);

        // then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        if (responseEntity.getBody() instanceof Task task) {
            assertNotNull(task.id());
            assertEquals(details, task.details());
            assertFalse(task.completed());

            assertEquals(URI.create("http://localhost:8080/api/tasks/" + task.id()),
                responseEntity.getHeaders().getLocation());

            verify(taskRepository).save(task);
        } else {
            assertInstanceOf(Task.class, responseEntity.getBody());
        }

        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    void testCreateNewTask_PayloadIsInvalid_ReturnsValidResponseEntity() {
        // given
        var details = "   ";
        var locale = Locale.US;
        var errorMessage = "Details is empty";

        doReturn(errorMessage).when(messageSource)
            .getMessage("tasks.create.details.errors.not_set", new Object[0], locale);

        // when
        var responseEntity = tasksRestController.createNewTask(new NewTaskPayload(details),
            UriComponentsBuilder.fromUriString("http://localhost:8080"), locale);

        // then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        assertEquals(new ErrorsPresentation(List.of(errorMessage)), responseEntity.getBody());

        verifyNoInteractions(taskRepository);
    }
}