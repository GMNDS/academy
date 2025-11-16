package com.gmnds.academy.controllers;

import com.gmnds.academy.models.GradeModel;
import com.gmnds.academy.models.InstitutionModel;
import com.gmnds.academy.services.GradeService;
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
public class GradeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private GradeService gradeService;

    @BeforeEach
    public void setup() {
        var controller = new GradeController();
        ReflectionTestUtils.setField(controller, "gradeService", gradeService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetAllGradesIncludesInstitutionName() throws Exception {
        var inst = new InstitutionModel();
        inst.setId(1L);
        inst.setName("Fatec");

        GradeModel g = new GradeModel();
        g.setId(1L);
        g.setName("P1");
        g.setWeight(0.3);
        g.setInstitution(inst);

        when(gradeService.findAll()).thenReturn(List.of(g));

        mockMvc.perform(get("/grades").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].institutionName").value("Fatec"));
    }
}
