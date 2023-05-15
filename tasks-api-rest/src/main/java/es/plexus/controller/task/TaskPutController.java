package es.plexus.controller.task;

import es.plexus.entity.task.Task;
import es.plexus.usecase.task.UpdateTaskForUserByIdUseCase;
import es.plexus.usecase.user.UpdateUserByIdUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app-task/1")
public class TaskPutController {

    @Autowired
    private UpdateTaskForUserByIdUseCase updateTaskForUserByIdUseCase;

    @PutMapping(path = "/users/{userId}/tasks/{taskId}")
    public ResponseEntity<Task> updateTaskForUserById(@PathVariable int userId,
                                                      @PathVariable int taskId,
                                                      @RequestBody Task task){

        updateTaskForUserByIdUseCase.updateTaskById(userId, taskId, task);

        return ResponseEntity.noContent().build();
    }
}
