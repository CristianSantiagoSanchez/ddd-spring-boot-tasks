package es.plexus.controller.user;

import es.plexus.entity.user.User;
import es.plexus.usecase.user.FindUserByIdUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/app-task/1")
public class UserGetController {
    @Autowired
    private FindUserByIdUseCase findUserByIdUseCase;


     @GetMapping(path = "/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {

        User user = findUserByIdUseCase.getUserById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
