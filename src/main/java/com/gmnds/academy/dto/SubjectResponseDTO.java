package com.gmnds.academy.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(description = "DTO de resposta com informações da disciplina")
public record SubjectResponseDTO(
        @Schema(description = "Identificador único da disciplina", example = "1")
        Long id,
        
        @Schema(description = "Nome da disciplina", example = "Matemática Básica")
        String name,
        
        @Schema(description = "ID do curso", example = "2")
        Long courseId,
        
        @Schema(description = "Nome do curso", example = "Desenvolvimento de Software Multiplataforma")
        String courseName,
        
        @Schema(description = "Código único da disciplina", example = "MAT101")
        String code,
        
        @Schema(description = "Quantidade total de aulas da matéria no semestre", example = "80")
        int totalClasses,
        
        @Schema(description = "ID do professor", example = "5")
        Long professorId,
        
        @Schema(description = "Nome do professor", example = "Dr. João Silva")
        String professorName
) implements Serializable {
}
