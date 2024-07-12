package pl.amilosh.value_objects;

import java.util.UUID;

// value object - instead of UUID personId
public final class PositionId {

    private final UUID positionId;

    public PositionId(UUID positionId) {
        this.positionId = positionId;
    }

    public UUID getPositionId() {
        return positionId;
    }
}
