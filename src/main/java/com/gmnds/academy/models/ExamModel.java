package com.gmnds.academy.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "exams")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ExamModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único da prova", example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    @JsonBackReference
    @Schema(description = "Disciplina da prova")
    private SubjectModel subject;

    @Column(name = "exam_date")
    @Schema(description = "Data da prova", example = "2025-11-20")
    private LocalDate examDate;
    
    @Schema(description = "Tipo da prova", example = "P1")
    private String type;
//    private Double weight;

}
