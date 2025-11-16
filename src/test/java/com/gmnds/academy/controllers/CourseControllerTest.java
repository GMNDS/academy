package com.gmnds.academy.controllers;

import com.gmnds.academy.models.CourseModel;
import com.gmnds.academy.models.InstitutionModel;
import com.gmnds.academy.services.CourseService;
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
public class CourseControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CourseService courseService;

    @BeforeEach
    public void setup() {
        var controller = new CourseController();
        ReflectionTestUtils.setField(controller, "courseService", courseService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetAllCoursesIncludesInstitutionName() throws Exception {
        var institution = InstitutionModel.builder().id(1L).name("Fatec").build();
        var course = CourseModel.builder().id(1L).name("DSMP").institution(institution).build();

        when(courseService.findAll()).thenReturn(List.of(course));

        mockMvc.perform(get("/courses").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].institutionName").value("Fatec"))
                .andExpect(jsonPath("$[0].name").value("DSMP"));
    }

    @Test
    public void testGetCourseByIdReturnsDto() throws Exception {
        var institution = InstitutionModel.builder().id(1L).name("Fatec").build();
        var course = CourseModel.builder().id(1L).name("DSMP").institution(institution).build();

        when(courseService.findById(1L)).thenReturn(course);

        mockMvc.perform(get("/courses/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.institutionName").value("Fatec"))
                .andExpect(jsonPath("$.name").value("DSMP"));
    }
}
