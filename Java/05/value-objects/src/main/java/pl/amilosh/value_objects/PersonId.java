package pl.amilosh.value_objects;

import java.util.UUID;

// value object - instead of UUID personId
// value object as record
public record PersonId(UUID positionId) {
}
