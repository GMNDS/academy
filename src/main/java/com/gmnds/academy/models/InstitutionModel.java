package com.gmnds.academy.models;

import jakarta.persistence.*;

@Entity
@Table(name = "institutions")
public class InstitutionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String ra;
    private String photo;
    
}
