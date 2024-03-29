package es.plexus.controller.user;

import es.plexus.entity.user.User;
import es.plexus.usecase.user.CreateUserUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/app-task/1")
public class UserRegisterController {

    @Autowired
    private CreateUserUseCase createUserUseCase;

    @PostMapping(path = "/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = createUserUseCase.createUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }
}
