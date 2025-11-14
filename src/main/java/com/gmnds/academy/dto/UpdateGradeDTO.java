package com.gmnds.academy.dto;

import com.gmnds.academy.models.InstitutionModel;
import com.gmnds.academy.models.SubjectModel;

import java.time.LocalDate;

public record UpdateGradeDTO(String name, Double weight, InstitutionModel institution) {
}


