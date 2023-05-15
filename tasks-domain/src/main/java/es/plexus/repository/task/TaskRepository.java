package es.plexus.repository.task;

import es.plexus.entity.task.Task;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository {
    public Task create(Task task);
    public List<Task> findAll();
    public Task findById(Integer id);
    public Task findByIdAndUserId(Integer userId, Integer id);
    public List<Task> findByUserId(Integer userId);
    public Task update(Task task);
    public void deleteById(int id);

}
