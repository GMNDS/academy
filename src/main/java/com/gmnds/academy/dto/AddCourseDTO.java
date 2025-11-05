package com.gmnds.academy.dto;

import com.gmnds.academy.models.CourseModel;

public record AddCourseDTO(String name, String institution, Integer duration, String category, Integer frequency) {
}
