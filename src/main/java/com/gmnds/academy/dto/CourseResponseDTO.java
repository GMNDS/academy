package com.gmnds.academy.dto;

import java.io.Serializable;

public record CourseResponseDTO(Long id, String name, String institutionName, Integer duration, String category, Integer frequency, boolean isActive) implements Serializable {
}
