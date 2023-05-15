package es.plexus.controller.task;

import es.plexus.entity.task.Task;
import es.plexus.entity.user.User;
import es.plexus.usecase.task.TaskService;
import es.plexus.usecase.user.UpdateUserByIdUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/app-task")
public class TaskGetAllForUserController {

    @Autowired
    private UpdateUserByIdUseCase userService;

    @Autowired
    private TaskService taskService;

    /*@GetMapping(path = "/users/{id}/tasks")
    public List<Task> getTasksForUser(@PathVariable int id) {
        Optional<User> user = userService.getUserById(id);
        return taskService.getTasks(user.get());

    }*/
}
