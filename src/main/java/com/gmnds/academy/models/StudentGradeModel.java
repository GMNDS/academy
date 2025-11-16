package com.gmnds.academy.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student_grades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentGradeModel implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único da nota do estudante", example = "1")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    @Schema(description = "Estudante associado à nota", example = "1", required = true)
    private StudentModel student;
    @ManyToOne
    @JoinColumn(name = "grade_id")
    @Schema(description = "Avaliação (P1, P2, etc) associada à nota", example = "1", required = true)
    private GradeModel grade;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    @JsonBackReference
    @Schema(description = "Disciplina associada à nota", example = "1", required = true)
    private SubjectModel subject;
    @Schema(description = "Nota obtida pelo estudante na avaliação", example = "8.5", required = true)
    private Double score;
}
