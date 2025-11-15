package com.gmnds.academy.dto;

import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para criação de tarefas: utilizamos IDs ao invés de entidades para requests leves.
 */
public record AddTaskDTO(
	@Schema(description = "ID do estudante que vai receber a tarefa", example = "1") Long studentId,
	@Schema(description = "ID da disciplina relacionada à tarefa", example = "2") Long subjectId,
	@Schema(description = "Título da tarefa", example = "Trabalho 1") String title,
	@Schema(description = "Descrição da tarefa", example = "Resolver exercícios do capítulo 5") String description,
	@Schema(description = "Data de entrega (ISO 8601)", example = "2025-11-30T23:59:59") LocalDateTime dueDate,
	@Schema(description = "Data de conclusão (ISO 8601) — opcional", example = "2025-12-01T10:20:00") LocalDateTime completedAt
) {}



