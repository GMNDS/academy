package com.gmnds.academy.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gmnds.academy.models.StudentGradeModel;


@Entity
@Table(name = "grades", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "institution_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GradeModel implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do peso da avaliação", example = "1")
    private Long id;

    @NotNull
    @Schema(description = "Nome da avaliação (P1,P2,P3,T)", example = "P1" )
    private String name;

    @NotNull
    @Min(0)
    @Max(1)
    @Schema(description = "Peso da avaliação (entre 0 e 1)", example = "0.3")
    private Double weight;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "institution_id")
    @Schema(description = "ID da Instituição de ensino associada ao peso da avaliação", example = "1")
    private InstitutionModel institution;

    @OneToMany(mappedBy = "grade", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private List<StudentGradeModel> studentGrades = new ArrayList<>();

}