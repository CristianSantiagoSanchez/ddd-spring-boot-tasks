package es.plexus.usecase.task;

import es.plexus.entity.task.StatusTask;
import es.plexus.entity.task.Task;
import es.plexus.entity.user.User;
import es.plexus.exceptions.user.UserNotFoundException;
import es.plexus.repository.task.TaskRepository;
import es.plexus.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateTaskUseCase {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public Task createTask(Integer userId, Task task){
        User userDB = userRepository.findById(userId);
        if (userDB == null){
            throw new UserNotFoundException("User not found with id " + userId);
        }

        task.setUser(userDB);
        task.setCreationDate(LocalDateTime.now());
        task.setStatus(StatusTask.TODO);

        return taskRepository.create(task);
    }
}
