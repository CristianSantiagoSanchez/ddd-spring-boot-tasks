package es.plexus.usecase.task;

import es.plexus.entity.task.Task;
import es.plexus.entity.user.User;
import es.plexus.exceptions.task.TaskNotFoundException;
import es.plexus.exceptions.user.UserNotFoundException;
import es.plexus.repository.task.TaskRepository;
import es.plexus.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindTaskForUserByIdUseCase {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;


    public Task getTaskById(int userId, int taskId){
        User userDB = userRepository.findById(userId);
        if (userDB == null){
            throw new UserNotFoundException("User not found with id " + userId);
        }
        Task taskDB = taskRepository.findByIdAndUserId(taskId, userId);
        if (taskDB == null){
            throw new TaskNotFoundException("Task not found with id " + taskId + " in user " + userId);
        }
        return taskDB;
    }

}
