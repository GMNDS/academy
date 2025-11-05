package com.gmnds.academy.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "professors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfessorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
//    private String email;
//    @ManyToOne
//    @JoinColumn(name = "institution_id")
//    private InstitutionModel institution;
}
