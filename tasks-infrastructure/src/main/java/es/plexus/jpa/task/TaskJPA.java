package es.plexus.jpa.task;


import es.plexus.entity.task.StatusTask;
import es.plexus.jpa.user.UserJPA;
import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
public class TaskJPA {

    @Id
    @GeneratedValue
    private int id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private StatusTask status;

    private LocalDateTime creationDate;

    @ManyToOne
    private UserJPA user;
}
