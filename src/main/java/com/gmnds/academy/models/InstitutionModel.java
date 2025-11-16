package com.gmnds.academy.models;

import com.gmnds.academy.enums.InstitutionTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "institutions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstitutionModel implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único da instituição", example = "1")
    private Long id;
    @Column(unique = true, nullable = false)
    @Schema(description = "Nome da instituição", example = "Faculdade de Técnologia de Mauá")
    private String name;
    @Schema(description = "Sigla da instituição", example = "FATEC")
    private String acronym;
    @Schema(description = "Tipo da instituição", example = "PUBLIC", allowableValues = {"SCHOOL", "HIGHER", "COURSE", "OTHER"})
    private InstitutionTypeEnum type;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
    @Column(nullable = false, columnDefinition = "boolean default true")
    @Builder.Default
    @Schema(description = "Indica se a instituição está ativa", example = "true")
    private boolean active = true;
}
