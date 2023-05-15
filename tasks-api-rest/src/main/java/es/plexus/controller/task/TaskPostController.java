package es.plexus.controller.task;

import es.plexus.entity.task.Task;
import es.plexus.entity.user.User;
import es.plexus.usecase.task.TaskService;
import es.plexus.usecase.user.UpdateUserByIdUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/app-task")
public class TaskPostController {

    @Autowired
    private UpdateUserByIdUseCase userService;

    @Autowired
    private TaskService taskService;

    /*@PostMapping(path = "/users/{id}/tasks")
    public ResponseEntity<User> createTaskForUser(@PathVariable int id, @Valid @RequestBody Task task) {
        Optional<User> user = userService.getUserById(id);

        Task createdTask = taskService.createTask(user.get(), task);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTask.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }*/
}
