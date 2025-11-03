package com.gmnds.academy.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name= "DISCIPLINA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class DisciplineModel {


    @Id
    @Column(name = "disciplina_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome")
    private String name;


    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private CourseModel id_curso;

    @Column(name = "frequencia_maxima")
    private int max_frequency;


    @ManyToOne
    @JoinColumn(name = "professor_id")
    private TeacherModel teacher;



}
