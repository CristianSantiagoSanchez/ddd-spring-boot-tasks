package es.plexus.controller.user;

import es.plexus.entity.user.User;
import es.plexus.usecase.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app-task/1")
public class UserUpdateController {
    @Autowired
    private UserService userService;

    @PutMapping(path = "/users/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable int id, @Valid @RequestBody User user){
        user.setId(id); // TODO debe ir service
        userService.updateUserById(id, user);
        return ResponseEntity.noContent().build();
    }
}
