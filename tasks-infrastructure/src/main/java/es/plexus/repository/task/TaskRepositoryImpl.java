package es.plexus.repository.task;

import es.plexus.entity.task.Task;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TaskRepositoryImpl implements TaskRepository{
    @Override
    public Optional<Task> create(Task task) {
        return Optional.empty();
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public Task save(Task task) {
        return null;
    }
}
