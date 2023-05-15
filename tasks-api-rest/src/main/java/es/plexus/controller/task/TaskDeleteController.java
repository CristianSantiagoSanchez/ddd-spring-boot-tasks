package es.plexus.controller.task;

import es.plexus.entity.task.Task;
import es.plexus.usecase.task.DeleteTaskForUserByIdUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app-task/1")
public class TaskDeleteController {

    @Autowired
    private DeleteTaskForUserByIdUseCase deleteTaskForUserByIdUseCase;


    @DeleteMapping(path = "/users/{userId}/tasks/{taskId}")
    public ResponseEntity<Task> deleteTaskForUserByd(@PathVariable int userId, @PathVariable int taskId) {

        deleteTaskForUserByIdUseCase.deleteTaskById(userId, taskId);

        return ResponseEntity.noContent().build();
    }
}
