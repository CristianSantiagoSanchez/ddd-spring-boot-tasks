package es.plexus.controller.task;

import es.plexus.entity.task.Task;
import es.plexus.entity.user.User;
import es.plexus.usecase.task.TaskService;
import es.plexus.usecase.user.UpdateUserByIdUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/app-task")
public class TaskPutController {

    @Autowired
    private UpdateUserByIdUseCase userService;

    @Autowired
    private TaskService taskService;

    /*@PutMapping(path = "/users/{userId}/tasks/{taskId}")
    public ResponseEntity<Task> updateTaskForUserById(@PathVariable int userId,
                                                      @PathVariable int taskId,
                                                      @RequestBody Task task){

        Optional<User> user = userService.getUserById(userId);


        taskService.updateTaskById(user.get(), taskId, task);

        return ResponseEntity.noContent().build();
    }*/
}
