package es.plexus.usecase.task;

import es.plexus.entity.task.Task;
import es.plexus.entity.user.User;
import es.plexus.exceptions.user.UserNotFoundException;
import es.plexus.repository.task.TaskRepository;
import es.plexus.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindAllTaskForUserUseCase {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Task> getTasks(int userId){
        User userDB = userRepository.findById(userId);
        if (userDB == null){
            throw new UserNotFoundException("User not found with id " + userId);
        }

        return taskRepository.findByUserId(userId);
    }
}
