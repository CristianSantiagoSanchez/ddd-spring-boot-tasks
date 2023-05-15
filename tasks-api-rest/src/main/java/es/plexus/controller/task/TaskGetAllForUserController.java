package es.plexus.controller.task;

import es.plexus.entity.task.Task;
import es.plexus.usecase.task.FindAllTaskForUserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app-task/1")
public class TaskGetAllForUserController {
    @Autowired
    private FindAllTaskForUserUseCase findAllTaskForUserUseCase;

    @GetMapping(path = "/users/{userId}/tasks")
    public ResponseEntity<List<Task>> getTasksForUser(@PathVariable int userId) {

        return new ResponseEntity<>(findAllTaskForUserUseCase.getTasks(userId), HttpStatus.OK);

    }
}
