package com.gmnds.academy.dto;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;

public record AddExamDTO(
	@Schema(description = "ID da disciplina", example = "2") Long subjectId,
	@Schema(description = "Data da prova (YYYY-MM-DD)", example = "2025-12-01") LocalDate exam_date,
	@Schema(description = "Tipo da prova", example = "P1") String type
) {
}


