package com.gmnds.academy.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(description = "DTO de resposta com informações do professor")
public record ProfessorResponseDTO(
        @Schema(description = "Identificador único do professor", example = "1")
        Long id,
        
        @Schema(description = "Nome completo do professor", example = "Dr. João Silva")
        String name
) implements Serializable {
}
