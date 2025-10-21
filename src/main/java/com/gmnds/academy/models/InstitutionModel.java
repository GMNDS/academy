package com.gmnds.academy.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "institutions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstitutionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String cnpj;
}
