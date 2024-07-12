package pl.amilosh.value_objects;

import java.util.UUID;

public interface PositionRepository {

    Position find(UUID positionId);

    Position findNew(PositionId positionId);
}
