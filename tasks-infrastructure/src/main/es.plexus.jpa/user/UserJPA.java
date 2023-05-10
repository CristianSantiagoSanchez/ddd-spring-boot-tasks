package es.plexus.entity.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity(name = "user_details")
public class User {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String email;

    private String password;

}