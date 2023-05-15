package es.plexus.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import es.plexus.entity.user.User;
import es.plexus.usecase.user.FindUserByIdUseCase;
import es.plexus.usecase.user.UpdateUserByIdUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app-task/1")
public class UserPatchController {

    @Autowired
    private UpdateUserByIdUseCase userService;

    @Autowired
    private FindUserByIdUseCase findUserByIdUseCase;


    @PatchMapping(path = "/users/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<User> updateUserByIdPartial(@PathVariable int id, @RequestBody JsonPatch patch){

        User user = findUserByIdUseCase.getUserById(id);
        User userPatched = null;
        try {
            userPatched = applyPatchToUser(patch, user);
        } catch (JsonPatchException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        userService.updateUserById(id, userPatched);
        return ResponseEntity.ok(userPatched);
    }

    private User applyPatchToUser(JsonPatch patch, User targetUser) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = null;
        patched = patch.apply(new ObjectMapper().convertValue(targetUser, JsonNode.class));
        return new ObjectMapper().treeToValue(patched, User.class);

    }
}
