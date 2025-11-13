package com.gmnds.academy.dto;

import java.io.Serializable;

public record AddCourseDTO(String name, String institution, Integer duration, String category, Integer frequency) implements Serializable {
}
