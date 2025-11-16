package com.gmnds.academy.controllers;

import com.gmnds.academy.models.TaskModel;
import com.gmnds.academy.models.StudentModel;
import com.gmnds.academy.models.SubjectModel;
import com.gmnds.academy.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TaskService taskService;

    @BeforeEach
    public void setup() {
        var controller = new TaskController();
        ReflectionTestUtils.setField(controller, "taskService", taskService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetAllTasksIncludesStudentAndSubject() throws Exception {
        StudentModel s = StudentModel.builder().id(1L).name("Gabriel").build();
        SubjectModel subj = new SubjectModel();
        subj.setId(2L);
        subj.setName("DSMP");

        TaskModel t = new TaskModel();
        t.setId(3L);
        t.setTitle("Trabalho 1");
        t.setStudent(s);
        t.setSubject(subj);
        t.setDueDate(LocalDateTime.parse("2025-11-30T23:59:59"));

        when(taskService.findAll()).thenReturn(List.of(t));

        mockMvc.perform(get("/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].studentName").value("Gabriel"))
                .andExpect(jsonPath("$[0].subjectName").value("DSMP"));
    }
}
