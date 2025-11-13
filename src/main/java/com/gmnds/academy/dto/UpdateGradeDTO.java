package com.gmnds.academy.dto;

import com.gmnds.academy.models.SubjectModel;

import java.time.LocalDate;

public record UpdateGradeDTO(SubjectModel subject, LocalDate exam_date, String type) {
}


