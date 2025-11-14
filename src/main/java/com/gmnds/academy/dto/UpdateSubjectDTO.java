package com.gmnds.academy.dto;

import com.gmnds.academy.models.CourseModel;
import com.gmnds.academy.models.ProfessorModel;
import com.gmnds.academy.models.SubjectModel;

import java.time.LocalDate;

public record UpdateSubjectDTO(String name, CourseModel course, String code, Integer totalClasses, ProfessorModel professor) {
}


