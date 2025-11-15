package com.gmnds.academy.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(description = "DTO de resposta com informações de frequência/falta")
public record AbsenceResponseDTO(
        @Schema(description = "Identificador único da falta/frequência", example = "1")
        Long id,
        
        @Schema(description = "ID do estudante", example = "3")
        Long studentId,
        
        @Schema(description = "Nome do estudante", example = "Orion Steele")
        String studentName,
        
        @Schema(description = "ID da disciplina", example = "2")
        Long subjectId,
        
        @Schema(description = "Nome da disciplina", example = "Matemática Básica")
        String subjectName,
        
        @Schema(description = "Quantidade total de aulas da disciplina no semestre", example = "80")
        int totalClasses,
        
        @Schema(description = "Número de presenças do estudante", example = "72")
        int attendances,
        
        @Schema(description = "Porcentagem de presença", example = "90.0")
        Double percentage
) implements Serializable {
}
