package com.gmnds.academy.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.Instant;

@Entity
@Table(name = "notes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ra_aluno")
    private UserModel raaluno;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private DisciplineModel disciplina;

    @Min(0)
    @Max(10)
    private Double p1;

    @Min(0)
    @Max(10)
    private Double p2;

    @Min(0)
    @Max(10)
    private Double t;

    @Min(0)
    @Max(10)
    private Double p3;

    @Transient
    public Double getFinalMedia() {
        if (p1 != null && p2 != null && t != null && p3 != null) {
            return (p1 + p2 + t + p3) / 4;
        }
        return null;
    }
}
