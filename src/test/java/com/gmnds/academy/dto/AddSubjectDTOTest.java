package com.gmnds.academy.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class AddSubjectDTOTest {

    private static Validator validator;

    @BeforeAll
    public static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidAddSubjectDTOWithProfessorName() {
        AddSubjectDTO dto = new AddSubjectDTO(
            "Matemática",
            2L,
            "MAT101",
            80,
            "João Silva"
        );

        Set<ConstraintViolation<AddSubjectDTO>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    public void testValidAddSubjectDTOWithoutProfessorName() {
        AddSubjectDTO dto = new AddSubjectDTO(
            "Física",
            3L,
            "FIS101",
            60,
            null
        );

        Set<ConstraintViolation<AddSubjectDTO>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    public void testAddSubjectDTOWithProfessorNameExceedingMaxLength() {
        String longName = "A".repeat(256); // 256 caracteres, excede o limite de 255
        
        AddSubjectDTO dto = new AddSubjectDTO(
            "Química",
            4L,
            "QUI101",
            70,
            longName
        );

        Set<ConstraintViolation<AddSubjectDTO>> violations = validator.validate(dto);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
            .contains("não pode exceder 255 caracteres");
    }

    @Test
    public void testAddSubjectDTOWithEmptyProfessorNameIsValid() {
        AddSubjectDTO dto = new AddSubjectDTO(
            "História",
            5L,
            "HIS101",
            50,
            ""
        );

        Set<ConstraintViolation<AddSubjectDTO>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }
}
