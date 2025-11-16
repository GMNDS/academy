package com.gmnds.academy.controllers;

import com.gmnds.academy.models.ExamModel;
import com.gmnds.academy.services.ExamService;
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
public class ExamControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ExamService examService;

    @BeforeEach
    public void setup() {
        var controller = new ExamController();
        ReflectionTestUtils.setField(controller, "examService", examService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetAllExams() throws Exception {
        ExamModel e = new ExamModel();
        e.setId(1L);
        e.setType("PROVA");

        when(examService.findAll()).thenReturn(List.of(e));

        mockMvc.perform(get("/exams").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].type").value("PROVA"));
    }
}
