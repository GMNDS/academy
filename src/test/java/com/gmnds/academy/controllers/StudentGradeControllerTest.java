package com.gmnds.academy.controllers;

import com.gmnds.academy.models.StudentGradeModel;
import com.gmnds.academy.models.StudentModel;
import com.gmnds.academy.models.GradeModel;
import com.gmnds.academy.services.StudentGradeService;
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
public class StudentGradeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StudentGradeService studentGradeService;

    @BeforeEach
    public void setup() {
        var controller = new StudentGradeController();
        ReflectionTestUtils.setField(controller, "studentGradeService", studentGradeService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetAllStudentGradesIncludesStudentAndGrade() throws Exception {
        StudentGradeModel sg = new StudentGradeModel();
        sg.setId(5L);
        sg.setStudent(StudentModel.builder().id(1L).name("Gabriel").build());
        sg.setGrade(GradeModel.builder().id(2L).name("P1").weight(0.3).build());
        sg.setScore(8.5);

        when(studentGradeService.findAll()).thenReturn(List.of(sg));

        mockMvc.perform(get("/studentgrades").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].studentName").value("Gabriel"))
                .andExpect(jsonPath("$[0].gradeName").value("P1"));
    }
}
