package com.gmnds.academy.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

public record AddGradeDTO(
	@Schema(description = "Nome da avaliação (ex: P1)", example = "P1") String name,
	@Schema(description = "Peso da avaliação (0..1)", example = "0.3") Double weight,
	@Schema(description = "ID da instituição associada", example = "1") Long institutionId
) {}


