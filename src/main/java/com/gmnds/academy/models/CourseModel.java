package com.gmnds.academy.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private InstitutionModel institution;
    private String name;
    private Integer duration; //Semestres
    private String category; //Exatas, Humanas, Biologicas, etc
    private Integer frequency;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
    @Column(nullable = false, columnDefinition = "boolean default true")
    @Builder.Default
    private boolean active = true;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DisciplineModel> disciplinas = new ArrayList<>();


}
