package com.gmnds.academy.dto;

import com.gmnds.academy.enums.InstitutionTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(description = "DTO de resposta com informações da instituição")
public record InstitutionResponseDTO(
        @Schema(description = "Identificador único da instituição", example = "1")
        Long id,
        
        @Schema(description = "Nome da instituição", example = "Faculdade de Tecnologia de Mauá")
        String name,
        
        @Schema(description = "Sigla da instituição", example = "FATEC")
        String acronym,
        
        @Schema(description = "Tipo da instituição", example = "PUBLIC")
        InstitutionTypeEnum type,
        
        @Schema(description = "Indica se a instituição está ativa", example = "true")
        boolean active
) implements Serializable {
}
