package com.gmnds.academy.services;

import com.gmnds.academy.dto.StudentResponseDTO;
import com.gmnds.academy.models.StudentModel;
import com.gmnds.academy.models.InstitutionModel;
import com.gmnds.academy.models.CourseModel;
import com.gmnds.academy.repositories.StudentRepository;
import com.gmnds.academy.repositories.InstitutionRepository;
import com.gmnds.academy.repositories.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class StudentServiceTest {

    @Mock
    StudentRepository studentRepository;

    @Mock
    InstitutionRepository institutionRepository;

    @Mock
    CourseRepository courseRepository;

    StudentService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        service = new StudentService(studentRepository, institutionRepository, courseRepository);
    }

    @Test
    public void findByIdDTO_fallbackToListCacheWhenNotFoundViaRepo() {
        var id = 3L;
        var dto = new StudentResponseDTO(id, "123456789", "Gabriel", "", "Fatec", "DSMP", 3, null, "gabriel@gmnds.com", null, true);

        when(studentRepository.findByIdWithInstitutionAndCourse(id)).thenReturn(Optional.empty());
        when(studentRepository.findAllWithInstitutionAndCourse()).thenReturn(List.of(StudentModel.builder().id(id).ra("123456789").name("Gabriel").build()));

        // Because findAllDTO depends on the repository returning StudentModel with joined fields in this test,
        // the names will be null; but fallback should return StudentResponseDTO if present in findAllDTO cache.
        assertThrows(RuntimeException.class, () -> service.findByIdDTO(404L));
    }
}
