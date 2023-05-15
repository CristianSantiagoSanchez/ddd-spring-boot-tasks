package es.plexus.controller.task;

import es.plexus.usecase.task.FindTaskForUserByIdUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app-task/1")
public class TaskGetController {

    @Autowired
    private FindTaskForUserByIdUseCase findTaskForUserByIdUseCase;

    @GetMapping(path = "/users/{userId}/tasks/{taskId}")
    public ResponseEntity<?> getTaskForUserById(@PathVariable int userId, @PathVariable int taskId) {

        return new ResponseEntity<>(findTaskForUserByIdUseCase.getTaskById(userId, taskId), HttpStatus.OK);

    }
}
