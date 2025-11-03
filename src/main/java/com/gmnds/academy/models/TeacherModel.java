package com.gmnds.academy.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "teachers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "nome")
    private String name;


    @Column(name= "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "instituicao_id")
    private InstitutionModel institution;
}
