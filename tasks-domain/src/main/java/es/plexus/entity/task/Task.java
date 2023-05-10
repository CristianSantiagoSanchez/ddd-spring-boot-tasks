package es.plexus.entity.task;

import es.plexus.entity.user.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class Task {

    private int id;

    @Size(min = 1, max = 20, message = "Title must have between 1 and 20 characters")
    @NotEmpty
    private String title;

    @Size(min = 1, max = 100, message = "Description must have between 1 and 100 characters")
    @NotEmpty
    private String description;

    private StatusTask status;

    private LocalDateTime creationDate;

    private User user;

}
