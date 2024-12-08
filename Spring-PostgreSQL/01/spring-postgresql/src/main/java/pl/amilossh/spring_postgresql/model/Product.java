package pl.amilossh.spring_postgresql.model;

import java.util.UUID;

public record Product(UUID id, String name) {
}
