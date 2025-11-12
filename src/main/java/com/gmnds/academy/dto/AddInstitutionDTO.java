package com.gmnds.academy.dto;

import com.gmnds.academy.enums.InstitutionTypeEnum;

public record AddInstitutionDTO(String name, String acronym, InstitutionTypeEnum type) {
}


