package com.gmnds.academy.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class TaskModel implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único da tarefa", example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference(value = "student-tasks")
    @Schema(description = "Estudante responsável pela tarefa")
    private StudentModel student;
    
    @ManyToOne
    @JoinColumn(name = "subject_id")
    @JsonBackReference(value = "subject-tasks")
    @Schema(description = "Disciplina da tarefa")
    private SubjectModel subject;
    
    @Schema(description = "Título da tarefa", example = "Trabalho de Matemática")
    private String title;
    
    @Schema(description = "Descrição detalhada da tarefa", example = "Resolver exercícios do capítulo 5")
    private String description;
    
    @Column(name = "due_date")
    @Schema(description = "Data de entrega da tarefa", example = "2025-11-30T23:59:59")
    private LocalDateTime dueDate;
    
    @Schema(description = "Status de conclusão da tarefa", example = "false")
    private Boolean completed;
    
    @Column(name = "completed_at")
    @Schema(description = "Data e hora em que a tarefa foi concluída", example = "2025-11-25T14:30:00")
    private LocalDateTime completedAt;



}
