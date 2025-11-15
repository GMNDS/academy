package com.gmnds.academy.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record AddStudentGradeDTO(
	@Schema(description = "ID do estudante", example = "1") Long studentId,
	@Schema(description = "ID do tipo de avaliação (peso)", example = "2") Long gradeId,
	@Schema(description = "ID da disciplina", example = "3") Long subjectId,
	@Schema(description = "Nota do estudante", example = "8.5") Double score
) {}


