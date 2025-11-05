package com.gmnds.academy.dto;

public record UpdateCourseDTO(String name, String institution, Integer duration, String category, Integer frequency, boolean isActive) {
}
