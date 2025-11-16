package com.gmnds.academy.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "absences")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AbsenceModel implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único da falta/frequência", example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    @Schema(description = "Estudante associado à frequência")
    private StudentModel student;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    @JsonBackReference
    @Schema(description = "Disciplina associada à frequência")
    private SubjectModel subject;

    @Column(name = "total_classes")
    @Schema(description = "Quantidade total de aulas da disciplina no semestre", example = "80")
    private int totalClasses;

    @Column(name = "attendances")
    @Schema(description = "Número de presenças do estudante", example = "72")
    private int attendances;

    @Schema(description = "Porcentagem de presença (calculada automaticamente)", example = "90.0")
    private Double percentage;


    @PrePersist
    @PreUpdate
    private void calculatePercentage() {
        if (totalClasses > 0) {
            this.percentage = (attendances * 100.0) / totalClasses;
        }
    }


}
