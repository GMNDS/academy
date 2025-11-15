package com.gmnds.academy.dto;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;

public record AddSubjectDTO(
	@Schema(description = "Nome da disciplina", example = "Matemática Básica") String name,
	@Schema(description = "ID do curso ao qual a disciplina pertence", example = "2") Long courseId,
	@Schema(description = "Código da disciplina", example = "MAT101") String code,
	@Schema(description = "Quantidade de aulas no semestre", example = "80") Integer totalClasses,
	@Schema(description = "ID do professor responsável (opcional)", example = "1") Long professorId
) {}


