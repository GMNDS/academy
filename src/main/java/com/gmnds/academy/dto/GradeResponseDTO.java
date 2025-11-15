package com.gmnds.academy.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(description = "DTO de resposta com informações do peso da avaliação")
public record GradeResponseDTO(
        @Schema(description = "Identificador único do peso da avaliação", example = "1")
        Long id,
        
        @Schema(description = "Nome da avaliação", example = "P1")
        String name,
        
        @Schema(description = "Peso da avaliação (entre 0 e 1)", example = "0.3")
        Double weight,
        
        @Schema(description = "ID da instituição", example = "1")
        Long institutionId,
        
        @Schema(description = "Nome da instituição", example = "Faculdade de Tecnologia de Mauá")
        String institutionName
) implements Serializable {
}
