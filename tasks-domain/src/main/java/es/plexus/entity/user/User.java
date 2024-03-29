package es.plexus.entity.user;
import es.plexus.entity.task.Task;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class User {
    private int id;

    @Size(min = 2, message = "Name should have at least 2 characters")
    @NotEmpty
    private String name;

    @Email(message = "Bad email")
    @NotEmpty
    private String email;

    @Size(min = 2, message = "Password should have at least 2 characters")
    @NotEmpty
    private String password;

    @Size(min = 2, message = "Name should have at least 2 characters")
    @NotEmpty
    private String username;

}