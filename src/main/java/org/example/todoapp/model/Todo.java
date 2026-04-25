package org.example.todoapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="Todo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "dueDate")
    private LocalDate dueDate;

    @Column(name = "status")
    private boolean status;

    @Column(name = "priority")
    private String priority;

}
