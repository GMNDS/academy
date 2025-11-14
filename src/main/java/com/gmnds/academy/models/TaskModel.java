package com.gmnds.academy.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private StudentModel student;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    @JsonBackReference
    private SubjectModel subject;
    private String title;
    private String description;
    @Column(name = "due_date")
    private LocalDateTime dueDate;
    private Boolean completed;
    @Column(name = "completed_at")
    private LocalDateTime completedAt;



}
