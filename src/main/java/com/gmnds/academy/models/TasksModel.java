package com.gmnds.academy.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "Tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TasksModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private DisciplineModel disciplina;

    @Column(name= "nome")
    private String name;

    @Column(name= "descricao")
    private String description;

    @Column(name = "data_entrega")
    private LocalDate delivery_date;

}
