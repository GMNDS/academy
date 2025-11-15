package com.gmnds.academy.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.time.LocalDate;

@Schema(description = "DTO de resposta com informações da prova")
public record ExamResponseDTO(
        @Schema(description = "Identificador único da prova", example = "1")
        Long id,
        
        @Schema(description = "ID da disciplina", example = "2")
        Long subjectId,
        
        @Schema(description = "Nome da disciplina", example = "Matemática Básica")
        String subjectName,
        
        @Schema(description = "Código da disciplina", example = "MAT101")
        String subjectCode,
        
        @Schema(description = "Data da prova", example = "2025-11-20")
        LocalDate examDate,
        
        @Schema(description = "Tipo da prova", example = "P1")
        String type
) implements Serializable {
}
