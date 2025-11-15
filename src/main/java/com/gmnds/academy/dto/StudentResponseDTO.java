package com.gmnds.academy.dto;

import com.gmnds.academy.enums.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(description = "DTO de resposta com informações do estudante")
public record StudentResponseDTO(
        @Schema(description = "Identificador único do estudante", example = "1")
        Long id,
        
        @Schema(description = "Registro Acadêmico do estudante", example = "20210012345")
        String ra,
        
        @Schema(description = "Nome completo do estudante", example = "Orion Steele")
        String name,
        
        @Schema(description = "URL da foto do estudante", example = "https://example.com/photo.jpg")
        String photo,
        
        @Schema(description = "Nome da instituição de ensino", example = "Faculdade de Tecnologia de Mauá")
        String institutionName,
        
        @Schema(description = "Nome do curso", example = "Desenvolvimento de Software Multiplataforma")
        String courseName,
        
        @Schema(description = "Semestre atual do estudante", example = "3")
        Integer semester,
        
        @Schema(description = "Média de notas do estudante", example = "8.5")
        Double averageGrade,
        
        @Schema(description = "Email do estudante", example = "orion@gmnds.com")
        String email,
        
        @Schema(description = "Papel do estudante no sistema", example = "STUDENT")
        UserRole role,
        
        @Schema(description = "Indica se o estudante está ativo", example = "true")
        boolean active
) implements Serializable {
}
