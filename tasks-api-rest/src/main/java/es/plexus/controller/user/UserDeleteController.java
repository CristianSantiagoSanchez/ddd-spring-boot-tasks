package es.plexus.controller.user;

import es.plexus.entity.user.User;
import es.plexus.usecase.user.DeleteUserByIdUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app-task/1")
public class UserDeleteController {

    @Autowired
    private DeleteUserByIdUseCase deleteUserByIdUseCase;

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable int id) {
        deleteUserByIdUseCase.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
