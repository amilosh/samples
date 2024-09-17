package pl.amilosh.spring_01.controller;

import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import pl.amilosh.spring_01.dto.NewTaskPayload;
import pl.amilosh.spring_01.exception.ErrorsPresentation;
import pl.amilosh.spring_01.model.Task;
import pl.amilosh.spring_01.repository.TaskRepository;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
public class TasksRestController {

    private final TaskRepository taskRepository;
    private final MessageSource messageSource;

    public TasksRestController(TaskRepository taskRepository,
                               MessageSource messageSource) {
        this.taskRepository = taskRepository;
        this.messageSource = messageSource;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(taskRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> createNewTask(
        @RequestBody NewTaskPayload payload,
        UriComponentsBuilder uriComponentsBuilder,
        Locale locale) {
        if (payload.details() == null || payload.details().isBlank()) {
            final var message = messageSource
                .getMessage("tasks.create.details.errors.not_set",
                    new Object[0], locale);
            return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorsPresentation(
                    List.of(message)));
        } else {
            var task = new Task(payload.details());
            taskRepository.save(task);
            return ResponseEntity.created(uriComponentsBuilder
                    .path("/api/tasks/{taskId}")
                    .build(Map.of("taskId", task.id())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(task);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Task> findTask(@PathVariable("id") UUID id) {
        return ResponseEntity.of(taskRepository.findById(id));
    }
}
