package com.gmnds.academy.dto;

public record SubjectCreateDTO(
        String name,
        Long courseId,
        String code,
        Integer totalClasses,
        String professorName
) {
}
