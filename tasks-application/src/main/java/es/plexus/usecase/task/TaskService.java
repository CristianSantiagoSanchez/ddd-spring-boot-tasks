package es.plexus.usecase.task;

import es.plexus.entity.task.StatusTask;
import es.plexus.entity.task.Task;
import es.plexus.entity.user.User;
import es.plexus.exceptions.task.TaskNotFoundException;
import es.plexus.repository.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getTasks(User user){
        //return user.getTasks();
        return new ArrayList<>();
    }

    public Task getTaskById(List<Task> tasks, int id){
        return tasks.stream()
                .filter(t -> t.getId()==id)
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException("TaskId: " + id + " do not exit."));
    }

    public Task createTask(User user, Task task){
        task.setUser(user);
        task.setCreationDate(LocalDateTime.now());
        task.setStatus(StatusTask.TODO);
        return taskRepository.save(task);
    }

    public void deleteTaskById(List<Task> tasks, int id){
        // comprobamos que existe una task con ese id en la lista de tasks del usuario
        Task task = tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException("TaskId: " + id + " do not exit."));
        taskRepository.deleteById(task.getId());
    }

    public void updateTaskById(User user, int id, Task task) {
        // comprobamos que existe una task con ese id en la lista de tasks del usuario
        /*user.getTasks().stream()
                .filter(t -> t.getId()==id)
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException("TaskId: " + id + " do not exit."));*/

        task.setId(id);
        task.setUser(user);
        taskRepository.save(task);
    }
}
