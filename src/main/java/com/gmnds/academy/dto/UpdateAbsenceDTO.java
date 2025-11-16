package com.gmnds.academy.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para atualização de registro de frequência/falta")
public record UpdateAbsenceDTO(
        @Schema(description = "ID do estudante", example = "3")
        Long studentId,

        @Schema(description = "ID da disciplina", example = "2")
        Long subjectId,

        @Schema(description = "Quantidade total de aulas da disciplina no semestre", example = "80")
        Integer totalClasses,

        @Schema(description = "Número de presenças do estudante", example = "72")
        Integer attendances
) {
}