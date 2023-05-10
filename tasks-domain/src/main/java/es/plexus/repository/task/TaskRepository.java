package es.plexus.repository.task;

import es.plexus.entity.task.Task;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository {

    Task save(Task task);

    void deleteById(int id);
}
