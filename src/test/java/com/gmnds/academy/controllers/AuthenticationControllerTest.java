package com.gmnds.academy.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmnds.academy.dto.ErrorDTO;
import com.gmnds.academy.models.CourseModel;
import com.gmnds.academy.models.InstitutionModel;
import com.gmnds.academy.models.StudentModel;
import com.gmnds.academy.repositories.InstitutionRepository;
import com.gmnds.academy.repositories.StudentRepository;
import com.gmnds.academy.services.CourseService;
import com.gmnds.academy.services.InstitutionService;
import com.gmnds.academy.infra.security.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AuthenticationControllerTest {

        private MockMvc mockMvc;

        private ObjectMapper objectMapper = new ObjectMapper();

        @Mock
        private AuthenticationManager authenticationManager;

        @Mock
        private StudentRepository repUser;

        // StudentService is not used by AuthenticationController (it uses StudentRepository directly)

        @Mock
        private TokenService tokenService;

        @Mock
        private com.gmnds.academy.services.StudentService studentService;

        @Mock
        private InstitutionService institutionService;

        @Mock
        private InstitutionRepository institutionRepository;

        @Mock
        private CourseService courseService;

        @BeforeEach
        public void setup() {
                var controller = new AuthenticationController();
                ReflectionTestUtils.setField(controller, "authenticationManager", authenticationManager);
                ReflectionTestUtils.setField(controller, "repUser", repUser);
                ReflectionTestUtils.setField(controller, "studentService", studentService);
                ReflectionTestUtils.setField(controller, "tokenService", tokenService);
                ReflectionTestUtils.setField(controller, "institutionService", institutionService);
                ReflectionTestUtils.setField(controller, "institutionRepository", institutionRepository);
                ReflectionTestUtils.setField(controller, "courseService", courseService);

                this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        }

    @Test
    public void testRegisterSuccessReturnsStudentResponseDTO() throws Exception {
        // Arrange
        when(repUser.findByEmail("gabriel@gmnds.com")).thenReturn(null);

        InstitutionModel institution = InstitutionModel.builder().id(1L).name("Fatec Mauá").acronym("FATEC").build();
        when(institutionRepository.findByNameIgnoreCase(any())).thenReturn(Optional.empty());
        when(institutionService.create(any())).thenReturn(institution);

        CourseModel course = CourseModel.builder().id(1L).name("Desenvolvimento de Software Multiplataforma").institution(institution).build();
        when(courseService.create(any())).thenReturn(course);

        StudentModel savedStudent = StudentModel.builder()
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

        when(studentService.create(any())).thenReturn(savedStudent);

        String requestJson = "{\n" +
                "\"institutionName\": \"Fatec Mauá\",\n" +
                "\"institutionAcronym\": \"FATEC\",\n" +
                "\"institutionType\": \"SCHOOL\",\n" +
                "\"courseName\": \"Desenvolvimento de Software Multiplataforma\",\n" +
                "\"courseDuration\": 6,\n" +
                "\"currentSemester\": 3,\n" +
                "\"courseCategory\": \"Tecnologia\",\n" +
                "\"weeklyFrequency\": 6,\n" +
                "\"ra\": \"123456789\",\n" +
                "\"name\": \"Gabriel Menezes da Silva\",\n" +
                "\"photo\": \"\",\n" +
                "\"email\": \"gabriel@gmnds.com\",\n" +
                "\"password\": \"fatec1234\"\n" +
                "}";

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.institutionName").value("Fatec Mauá"))
                .andExpect(jsonPath("$.courseName").value("Desenvolvimento de Software Multiplataforma"));
    }

    @Test
    public void testRegisterDuplicateEmailReturnsErrorDTO() throws Exception {
        when(repUser.findByEmail("already@exists.com")).thenReturn(new StudentModel());

        String requestJson = "{\n" +
                "\"institutionName\": \"Fatec Mauá\",\n" +
                "\"institutionAcronym\": \"FATEC\",\n" +
                "\"institutionType\": \"SCHOOL\",\n" +
                "\"courseName\": \"Desenvolvimento de Software Multiplataforma\",\n" +
                "\"courseDuration\": 6,\n" +
                "\"currentSemester\": 3,\n" +
                "\"courseCategory\": \"Tecnologia\",\n" +
                "\"weeklyFrequency\": 6,\n" +
                "\"ra\": \"987654321\",\n" +
                "\"name\": \"Outro\",\n" +
                "\"photo\": \"\",\n" +
                "\"email\": \"already@exists.com\",\n" +
                "\"password\": \"pass\"\n" +
                "}";

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("E-mail já cadastrado"));
    }
}
