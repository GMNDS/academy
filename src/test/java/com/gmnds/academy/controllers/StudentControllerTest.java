package com.gmnds.academy.controllers;

import com.gmnds.academy.models.StudentModel;
import com.gmnds.academy.models.InstitutionModel;
import com.gmnds.academy.models.CourseModel;
import com.gmnds.academy.services.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @Mock
    private com.gmnds.academy.infra.security.TokenService tokenService;

    @BeforeEach
    public void setup() {
        var controller = new StudentController();
        ReflectionTestUtils.setField(controller, "studentService", studentService);
        ReflectionTestUtils.setField(controller, "tokenService", tokenService);

        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetAllStudentsIncludesInstitutionAndCourseNames() throws Exception {
        InstitutionModel institution = InstitutionModel.builder().id(1L).name("Fatec Mauá").build();
        CourseModel course = CourseModel.builder().id(1L).name("Desenvolvimento de Software Multiplataforma").institution(institution).build();

        StudentModel student = StudentModel.builder()
                .id(5L)
                .ra("123456789")
                .name("Gabriel Menezes da Silva")
                .photo("")
                .institution(institution)
                .course(course)
                .semester(3)
                .email("gabriel@gmnds.com")
                .role(com.gmnds.academy.enums.UserRole.STUDENT)
                .active(true)
                .build();

        when(studentService.findAllDTO()).thenReturn(List.of(new com.gmnds.academy.dto.StudentResponseDTO(
            student.getId(),
            student.getRa(),
            student.getName(),
            student.getPhoto(),
            student.getInstitution().getName(),
            student.getCourse().getName(),
            student.getSemester(),
            student.getAverage_grade(),
            student.getEmail(),
            student.getRole(),
            student.isActive()
        )));

        mockMvc.perform(get("/students").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].institutionName").value("Fatec Mauá"))
                .andExpect(jsonPath("$[0].courseName").value("Desenvolvimento de Software Multiplataforma"));
    }

        @Test
        public void testGetStudentByIdIncludesInstitutionAndCourseNames() throws Exception {
        var id = 5L;
        var dto = new com.gmnds.academy.dto.StudentResponseDTO(
            id,
            "123456789",
            "Gabriel",
            "",
            "Fatec Mauá",
            "Desenvolvimento de Software",
            3,
            null,
            "gabriel@gmnds.com",
            com.gmnds.academy.enums.UserRole.STUDENT,
            true
        );

        when(tokenService.getUserId("token123")).thenReturn(id);
        when(studentService.findByIdDTO(id)).thenReturn(dto);

        mockMvc.perform(get("/students/5").header("Authorization","Bearer token123").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.institutionName").value("Fatec Mauá"))
            .andExpect(jsonPath("$.courseName").value("Desenvolvimento de Software"));
        }
}
