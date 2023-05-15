package es.plexus.repository.task;

import es.plexus.entity.task.Task;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository {

    Optional<Task> create(Task task);

    void deleteById(int id);

    Task save(Task task);
}
