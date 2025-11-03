package com.gmnds.academy.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "Tests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private DisciplineModel disciplina;

    @Column(name = "data_prova")
    private LocalDate date_Test;

    @Column(name = "tipo")
    private String type;

}
