package com.gmnds.academy.dto;

import java.io.Serializable;

public record UpdateCourseDTO(String name, String institution, Integer duration, String category, Integer frequency, boolean isActive) implements Serializable {
}
