package es.plexus.jpa.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity(name = "user_details")
public class UserJPA {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String email;

    private String password;

    private String username;

}