package es.plexus.controller.task;

import es.plexus.entity.user.User;
import es.plexus.usecase.task.TaskService;
import es.plexus.usecase.user.UpdateUserByIdUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/app-task")
public class TaskGetController {

    @Autowired
    private UpdateUserByIdUseCase userService;

    @Autowired
    private TaskService taskService;

    /*@GetMapping(path = "/users/{userId}/tasks/{taskId}")
    public ResponseEntity<?> getTaskForUserById(@PathVariable int userId, @PathVariable int taskId){
        Optional<User> user = userService.getUserById(userId);
        //Task task = taskService.getTaskById(user.get().getTasks(), taskId);

        return ResponseEntity.noContent().build();

    }*/
}
