package com.gmnds.academy.controllers;

import com.gmnds.academy.models.SubjectModel;
import com.gmnds.academy.models.CourseModel;
import com.gmnds.academy.models.ProfessorModel;
import com.gmnds.academy.services.SubjectService;
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
public class SubjectControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SubjectService subjectService;

    @BeforeEach
    public void setup() {
        var controller = new SubjectController();
        ReflectionTestUtils.setField(controller, "subjectService", subjectService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetAllSubjectsIncludesCourseAndProfessor() throws Exception {
        var course = CourseModel.builder().id(2L).name("DSMP").build();
        var prof = ProfessorModel.builder().id(1L).name("Dr. A").build();

        SubjectModel s = new SubjectModel();
        s.setId(10L);
        s.setName("Matemática");
        s.setCourse(course);
        s.setProfessor(prof);

        when(subjectService.findAll()).thenReturn(List.of(s));

        mockMvc.perform(get("/subjects").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].courseName").value("DSMP"))
                .andExpect(jsonPath("$[0].professorName").value("Dr. A"));
    }
}
