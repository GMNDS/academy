package com.gmnds.academy.controllers;

import com.gmnds.academy.models.ProfessorModel;
import com.gmnds.academy.services.ProfessorService;
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
public class ProfessorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProfessorService professorService;

    @BeforeEach
    public void setup() {
        var controller = new ProfessorController();
        ReflectionTestUtils.setField(controller, "professorService", professorService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetAllProfessors() throws Exception {
        ProfessorModel p = new ProfessorModel();
        p.setId(1L);
        p.setName("Prof A");

        when(professorService.findAll()).thenReturn(List.of(p));

        mockMvc.perform(get("/professors").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Prof A"));
    }
}
