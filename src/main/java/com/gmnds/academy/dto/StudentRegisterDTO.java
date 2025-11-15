package com.gmnds.academy.dto;

import com.gmnds.academy.enums.InstitutionTypeEnum;

public record StudentRegisterDTO(
        // Dados da Instituição
        String institutionName,
        String institutionAcronym,
        InstitutionTypeEnum institutionType,
        
        // Dados do Curso
        String courseName,
        Integer courseDuration,
        Integer currentSemester,
        String courseCategory,
        Integer weeklyFrequency,
        
        // Dados do Estudante
        String ra,
        String name,
        String photo,
        String email,
        String password
) {
}
