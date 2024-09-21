package pl.amilosh.spring_01.model;

import java.util.UUID;

public record Task(
    UUID id,
    String details,
    boolean completed,
    UUID userId) {

    public Task(String details, UUID userId) {
        this(UUID.randomUUID(), details, false, userId);
    }
}
