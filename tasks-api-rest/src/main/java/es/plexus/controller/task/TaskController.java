package es.plexus.controller.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import es.plexus.entity.task.Task;
import es.plexus.entity.user.User;
import es.plexus.usecase.task.TaskService;
import es.plexus.usecase.user.UserService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/app-task")
public class TaskController {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    public TaskController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping(path = "/users/{id}/tasks")
    public List<Task> getTasksForUser(@PathVariable int id) {
        Optional<User> user = userService.getUserById(id);
        return taskService.getTasks(user.get());

    }
   /* @GetMapping(path = "/users/{userId}/tasks/{taskId}")
    public ResponseEntity<Task> getTaskForUserById(@PathVariable int userId, @PathVariable int taskId){
        Optional<User> user = userService.getUserById(userId);
        Task task = taskService.getTaskById(user.get().getTasks(), taskId);

        return EntityModel.of(task);

    }*/

    @PostMapping(path = "/users/{id}/tasks")
    public ResponseEntity<User> createTaskForUser(@PathVariable int id, @Valid @RequestBody Task task) {
        Optional<User> user = userService.getUserById(id);

        Task createdTask = taskService.createTask(user.get(), task);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTask.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }

  /*  @DeleteMapping(path = "/users/{userId}/tasks/{taskId}")
    public ResponseEntity<Task> deleteTaskForUserByd(@PathVariable int userId, @PathVariable int taskId) {
        Optional<User> user = userService.getUserById(userId);

        taskService.deleteTaskById(user.get().getTasks(), taskId);

        return ResponseEntity.noContent().build();
    }*/

    @PutMapping(path = "/users/{userId}/tasks/{taskId}")
    public ResponseEntity<Task> updateTaskForUserById(@PathVariable int userId,
                                                      @PathVariable int taskId,
                                                      @RequestBody Task task){

        Optional<User> user = userService.getUserById(userId);


        taskService.updateTaskById(user.get(), taskId, task);

        return ResponseEntity.noContent().build();
    }

/*
    @PatchMapping(path = "/users/{userId}/tasks/{taskId}", consumes = "application/json-patch+json")
    public ResponseEntity<Task> updateTaskForUserByIdPartial(
            @PathVariable int userId,
            @PathVariable int taskId,
            @RequestBody JsonPatch patch) {

        User user = userService.getUserById(userId).get();
        Task task = taskService.getTaskById(user.getTasks(), taskId);


        Task taskPatched = null;
        try {
            taskPatched = applyPatchToUser(patch, task);
        } catch (JsonPatchException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        taskPatched.setUser(user);
        taskService.updateTaskById(user, taskId, taskPatched);

        return ResponseEntity.ok(taskPatched);
    }
*/

    private Task applyPatchToUser(JsonPatch patch, Task targetTask) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper  = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        JsonNode patched = null;
        patched = patch.apply(objectMapper.convertValue(targetTask, JsonNode.class));
        return objectMapper.treeToValue(patched, Task.class);

    }
}
