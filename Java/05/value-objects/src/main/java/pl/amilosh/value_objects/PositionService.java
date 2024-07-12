package pl.amilosh.value_objects;

import java.util.UUID;

public class PositionService {

    WorkerRepository workerRepository;
    PositionRepository positionRepository;

    public void process(UUID workerId) {
        /* old */
        var workerOld = workerRepository.find(workerId);
        // instead of workerOld.personId - wrong
        var position = positionRepository.find(workerOld.personId);

        /* new */
        var workerNew = workerRepository.findNew(workerId);
        // we can use workerNew.positionId only
        var position2 = positionRepository.findNew(workerNew.positionId);
    }
}
