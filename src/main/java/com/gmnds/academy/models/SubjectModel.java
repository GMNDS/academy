package com.gmnds.academy.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name= "subjects",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"teacher_id", "course_id", "name"}
    ))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectModel implements java.io.Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "subject_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único da disciplina", example = "1")
    private Long id;
    @Schema(description = "Nome da disciplina", example = "Matemática Básica")
    private String name;
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    @JsonBackReference
    @Schema(description = "ID do curso ao qual a disciplina pertence", example = "2")
    private CourseModel course;
    @Column(unique = true, nullable = false)
    @Schema(description = "Código único da disciplina", example = "MAT101")
    private String code;
    @Column(name = "total_classes")
    @Schema(description = "Quantidade total de aulas da matéria no semestre", example = "80")
    @Min(1)
    private int totalClasses;


    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private ProfessorModel professor;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private List<StudentGradeModel> studentGrades = new ArrayList<>();

    @OneToMany(mappedBy = "subject", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private List<ExamModel> exams = new ArrayList<>();

    @OneToMany(mappedBy = "subject", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private List<TaskModel> tasks = new ArrayList<>();

    @OneToMany(mappedBy = "subject", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private List<AbsenceModel> absences = new ArrayList<>();

}
