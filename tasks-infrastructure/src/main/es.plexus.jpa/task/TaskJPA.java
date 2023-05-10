package es.plexus.entity.task;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
public class Task {

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
