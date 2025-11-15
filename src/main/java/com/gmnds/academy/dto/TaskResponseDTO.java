package com.gmnds.academy.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.time.LocalDateTime;

@Schema(description = "DTO de resposta com informações da tarefa")
public record TaskResponseDTO(
        @Schema(description = "Identificador único da tarefa", example = "1")
        Long id,
        
        @Schema(description = "ID do estudante", example = "3")
        Long studentId,
        
        @Schema(description = "Nome do estudante", example = "Orion Steele")
        String studentName,
        
        @Schema(description = "ID da disciplina", example = "2")
        Long subjectId,
        
        @Schema(description = "Nome da disciplina", example = "Matemática Básica")
        String subjectName,
        
        @Schema(description = "Título da tarefa", example = "Trabalho de Matemática")
        String title,
        
        @Schema(description = "Descrição detalhada da tarefa", example = "Resolver exercícios do capítulo 5")
        String description,
        
        @Schema(description = "Data de entrega da tarefa", example = "2025-11-30T23:59:59")
        LocalDateTime dueDate,
        
        @Schema(description = "Status de conclusão da tarefa", example = "false")
        Boolean completed,
        
        @Schema(description = "Data e hora em que a tarefa foi concluída", example = "2025-11-25T14:30:00")
        LocalDateTime completedAt
) implements Serializable {
}
