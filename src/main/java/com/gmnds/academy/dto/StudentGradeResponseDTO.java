package com.gmnds.academy.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(description = "DTO de resposta com informações da nota do estudante")
public record StudentGradeResponseDTO(
        @Schema(description = "Identificador único da nota do estudante", example = "1")
        Long id,
        
        @Schema(description = "ID do estudante", example = "3")
        Long studentId,
        
        @Schema(description = "Nome do estudante", example = "Orion Steele")
        String studentName,
        
        @Schema(description = "ID do peso da avaliação", example = "2")
        Long gradeId,
        
        @Schema(description = "Nome da avaliação", example = "P1")
        String gradeName,
        
        @Schema(description = "Peso da avaliação", example = "0.3")
        Double gradeWeight,
        
        @Schema(description = "ID da disciplina", example = "1")
        Long subjectId,
        
        @Schema(description = "Nome da disciplina", example = "Matemática Básica")
        String subjectName,
        
        @Schema(description = "Nota obtida pelo estudante na avaliação", example = "8.5")
        Double score
) implements Serializable {
}
