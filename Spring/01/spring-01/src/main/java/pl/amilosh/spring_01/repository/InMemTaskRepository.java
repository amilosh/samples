package pl.amilosh.spring_01.repository;

import pl.amilosh.spring_01.model.Task;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InMemTaskRepository implements TaskRepository {

    private final List<Task> tasks = new LinkedList<>();

    @Override
    public List<Task> findAll() {
        return tasks;
    }

    @Override
    public void save(Task task) {
        tasks.add(task);
    }

    @Override
    public Optional<Task> findById(UUID id) {
        return tasks.stream()
            .filter(task -> task.id().equals(id))
            .findFirst();
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
