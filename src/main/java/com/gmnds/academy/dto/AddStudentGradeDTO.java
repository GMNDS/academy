package com.gmnds.academy.dto;

import com.gmnds.academy.models.GradeModel;
import com.gmnds.academy.models.StudentModel;
import com.gmnds.academy.models.SubjectModel;

import java.time.LocalDate;

public record AddStudentGradeDTO(StudentModel student, GradeModel grade, SubjectModel subject, Double score) {
}


