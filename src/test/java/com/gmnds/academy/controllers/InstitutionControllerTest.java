package com.gmnds.academy.controllers;

import com.gmnds.academy.models.InstitutionModel;
import com.gmnds.academy.services.InstitutionService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class InstitutionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private InstitutionService institutionService;

    @BeforeEach
    public void setup() {
        var controller = new InstitutionController();
        ReflectionTestUtils.setField(controller, "institutionService", institutionService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetAllInstitutions() throws Exception {
        InstitutionModel inst = new InstitutionModel();
        inst.setId(1L);
        inst.setName("Fatec Maua");

        when(institutionService.findAll()).thenReturn(List.of(inst));

        mockMvc.perform(get("/institutions").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Fatec Maua"));
    }
}
