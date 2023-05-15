package es.plexus.repository.task;

import es.plexus.entity.task.Task;
import es.plexus.jpa.task.TaskJPA;
import es.plexus.mapper.task.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepositoryImpl implements TaskRepository{

    @Autowired
    private TaskJpaRepository taskJpaRepository;

    @Autowired
    private TaskMapper taskMapper;
    @Override
    public Task create(Task task) {
        return taskMapper.toEntity(taskJpaRepository.save(taskMapper.toJpa(task)));
    }

    @Override
    public List<Task> findAll() {
        return taskMapper.toListEntity(taskJpaRepository.findAll());
    }

    @Override
    public Task findById(Integer id) {
        Optional<TaskJPA> taskJPA = taskJpaRepository.findById(id);
        if (taskJPA.isEmpty()){
            return null;
        }
        return taskMapper.toEntity(taskJPA.get());
    }
    @Override
    public Task findByIdAndUserId(Integer userId, Integer id) {
        Optional<TaskJPA> taskJPA = taskJpaRepository.findByIdAndUserId(id, userId);
        if (taskJPA.isEmpty()){
            return null;
        }
        return taskMapper.toEntity(taskJPA.get());
    }

    @Override
    public List<Task> findByUserId(Integer userId) {
        List<TaskJPA> taskJPA = taskJpaRepository.findByUserId(userId);
        if (taskJPA.isEmpty()){
            return null;
        }
        return taskMapper.toListEntity(taskJPA);
    }

    @Override
    public Task update(Task task) {
        return taskMapper.toEntity(taskJpaRepository.save(taskMapper.toJpa(task)));
    }

    @Override
    public void deleteById(int id) {
        taskJpaRepository.deleteById(id);
    }

}
