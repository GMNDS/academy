package com.gmnds.academy.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de erro com mensagem legível")
public record ErrorDTO(
        @Schema(description = "Mensagem de erro amigável", example = "Campo X é obrigatório")
        String message
) {
}
