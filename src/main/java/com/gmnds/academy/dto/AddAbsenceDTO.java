package com.gmnds.academy.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para criação de registro de frequência/falta")
public record AddAbsenceDTO(
        @Schema(description = "ID do estudante", example = "2", required = true)
        Long studentId,

        @Schema(description = "ID da disciplina", example = "1", required = true)
        Long subjectId,

        @Schema(description = "Quantidade total de aulas da disciplina no semestre", example = "80", required = true)
        int totalClasses,

        @Schema(description = "Número de presenças do estudante", example = "72", required = true)
        int attendances
) {
}