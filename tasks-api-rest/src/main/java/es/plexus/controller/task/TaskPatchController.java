package es.plexus.controller.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import es.plexus.entity.task.Task;
import es.plexus.usecase.task.TaskService;
import es.plexus.usecase.user.UpdateUserByIdUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app-task")
public class TaskPatchController {
    @Autowired
    private UpdateUserByIdUseCase userService;

    @Autowired
    private TaskService taskService;

    /*@PatchMapping(path = "/users/{userId}/tasks/{taskId}", consumes = "application/json-patch+json")
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
    }*/

    private Task applyPatchToUser(JsonPatch patch, Task targetTask) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        JsonNode patched = null;
        patched = patch.apply(objectMapper.convertValue(targetTask, JsonNode.class));
        return objectMapper.treeToValue(patched, Task.class);

    }
}
