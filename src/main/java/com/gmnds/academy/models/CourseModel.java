package com.gmnds.academy.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class CourseModel implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do curso", example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    @Schema(description = "ID da instituição à qual o curso pertence", example = "1")
    private InstitutionModel institution;
    @Schema(description = "Nome do curso", example = "Desenvolvimento de software Multi Plataforma")
    private String name;
    @Schema(description = "Duração do curso em semestres", example = "6")
    private Integer duration;
    @Schema(description = "Categoria do curso (Exatas, Humanas, Biologicas, etc)", example = "Exatas")
    private String category;
    @Schema(description = "Frequência do curso por semana", example = "5")
    private Integer frequency;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
    @Column(nullable = false, columnDefinition = "boolean default true")
    @Builder.Default
    private boolean active = true;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @JsonIgnore
    private List<SubjectModel> subjects = new ArrayList<>();


}
