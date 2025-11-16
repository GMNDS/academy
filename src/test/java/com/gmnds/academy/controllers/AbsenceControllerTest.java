package com.gmnds.academy.controllers;

import com.gmnds.academy.models.AbsenceModel;
import com.gmnds.academy.models.StudentModel;
import com.gmnds.academy.models.SubjectModel;
import com.gmnds.academy.services.AbsenceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AbsenceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AbsenceService absenceService;

    @BeforeEach
    public void setup() {
        var controller = new AbsenceController();
        ReflectionTestUtils.setField(controller, "absenceService", absenceService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetAllAbsencesIncludesStudentAndSubject() throws Exception {
        var student = StudentModel.builder().id(1L).name("Gabriel").build();
        var subject = SubjectModel.builder().id(2L).name("DSMP").build();

        AbsenceModel a = new AbsenceModel();
        a.setId(10L);
        a.setStudent(student);
        a.setSubject(subject);
        a.setTotalClasses(30);
        a.setAttendances(25);

        when(absenceService.findAll()).thenReturn(List.of(a));

        mockMvc.perform(get("/absences").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].studentName").value("Gabriel"))
                .andExpect(jsonPath("$[0].subjectName").value("DSMP"));
    }
}
