package es.plexus.controller.task;

import es.plexus.entity.task.Task;
import es.plexus.entity.user.User;
import es.plexus.usecase.task.CreateTaskUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/app-task/1")
public class TaskPostController {

    @Autowired
    private CreateTaskUseCase createTaskUseCase;


    @PostMapping(path = "/users/{userId}/tasks")
    public ResponseEntity<User> createTaskForUser(@PathVariable int userId, @Valid @RequestBody Task task) {

        Task createdTask = createTaskUseCase.createTask(userId, task);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{taskId}")
                .buildAndExpand(createdTask.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }
}
