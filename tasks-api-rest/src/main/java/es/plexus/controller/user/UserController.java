package es.plexus.controller.user;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import es.plexus.entity.user.User;
import es.plexus.usecase.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/app-task")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }


   /* @GetMapping(path = "/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        
        Optional<User> user = userService.getUserById(id);

        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }*/


    @PostMapping(path = "/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = userService.createUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }


    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable int id
    ) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }



    @PutMapping(path = "/users/{id}")
    public ResponseEntity<User> updateUserById( @PathVariable int id, @Valid @RequestBody User user){
        user.setId(id);
        userService.updateUserById(id, user);
        return ResponseEntity.noContent().build();
    }


    @PatchMapping(path = "/users/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<User> updateUserByIdPartial(@PathVariable int id, @RequestBody JsonPatch patch){

        User user = userService.getUserById(id).get();
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
