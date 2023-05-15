package es.plexus.controller.task;

import es.plexus.entity.task.Task;
import es.plexus.entity.user.User;
import es.plexus.usecase.user.UpdateUserByIdUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/app-task")
public class TaskDeleteController {

    @Autowired
    private UpdateUserByIdUseCase userService;


    @DeleteMapping(path = "/users/{userId}/tasks/{taskId}")
    public ResponseEntity<Task> deleteTaskForUserByd(@PathVariable int userId, @PathVariable int taskId) {
        //Optional<User> user = userService.getUserById(userId);

        //taskService.deleteTaskById(user.get().getTasks(), taskId);

        return ResponseEntity.noContent().build();
    }
}
