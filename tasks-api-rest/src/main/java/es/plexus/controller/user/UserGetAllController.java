package es.plexus.controller.user;

import es.plexus.entity.user.User;
import es.plexus.usecase.user.FindAllUsersUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app-task/1")
public class UserGetAllController {

    @Autowired
    private FindAllUsersUseCase findAllUsersUseCase;

    @GetMapping(path = "/users")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(findAllUsersUseCase.getUsers(), HttpStatus.OK);
    }
}
