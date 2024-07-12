package pl.amilosh.value_objects;

import java.util.UUID;

public interface WorkerRepository {

    WorkerOld find(UUID workerId);
    WorkerNew findNew(UUID workerId);
}
