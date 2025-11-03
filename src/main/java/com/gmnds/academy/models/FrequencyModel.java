package com.gmnds.academy.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Frequency")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class FrequencyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ra_aluno")
    private UserModel usuario;

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private DisciplineModel disciplina;

    @Column(name= "aulas_ministradas")
    private int aulas_ministradas;

    @Column(name = "presencas")
    private int presencas;

    @Column(name = "percentual")
    private Double percentual;


}
