package com.gmnds.academy.controllers;

import com.gmnds.academy.dto.LoginDTO;
import com.gmnds.academy.dto.RegisterDTO;
import com.gmnds.academy.dto.StudentRegisterDTO;
import com.gmnds.academy.enums.UserRole;
import com.gmnds.academy.infra.security.TokenService;
import com.gmnds.academy.models.CourseModel;
import com.gmnds.academy.models.InstitutionModel;
import com.gmnds.academy.models.StudentModel;
import com.gmnds.academy.repositories.InstitutionRepository;
import com.gmnds.academy.repositories.StudentRepository;
import com.gmnds.academy.services.CourseService;
import com.gmnds.academy.services.InstitutionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import com.gmnds.academy.dto.AuthenticationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("auth")
@RestController
@Tag(name = "Autenticação", description = "Endpoints de autenticação e registro de usuários")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private StudentRepository repUser;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private com.gmnds.academy.services.StudentService studentService;

    @PostMapping("login")
    @Operation(summary = "Login", description = "Autentica um usuário e retorna um token JWT")
    public ResponseEntity<LoginDTO> login(@RequestBody @Valid AuthenticationDTO data) {
        var userPassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(userPassword);

        var token = tokenService.generateToken((StudentModel) auth.getPrincipal());

        return ResponseEntity.ok(new LoginDTO(token));
    }

    @PostMapping("register")
    @Operation(summary = "Registrar estudante", description = "Registra um novo estudante com instituição e curso")
    public ResponseEntity<?> register(@RequestBody @Valid StudentRegisterDTO data) {
        try {
            // Valida se o e-mail já existe
            if (this.repUser.findByEmail(data.email()) != null) {
                return ResponseEntity.badRequest().body(new com.gmnds.academy.dto.ErrorDTO("E-mail já cadastrado"));
            }

            // 1. Verifica ou cria a instituição
            Optional<InstitutionModel> existingInstitution = 
                institutionRepository.findByNameIgnoreCase(data.institutionName());
            
            InstitutionModel institution;
            if (existingInstitution.isPresent()) {
                institution = existingInstitution.get();
            } else {
                institution = InstitutionModel.builder()
                    .name(data.institutionName())
                    .acronym(data.institutionAcronym())
                    .type(data.institutionType())
                    .active(true)
                    .build();
                institution = institutionService.create(institution);
            }

            // 2. Cria o curso vinculado à instituição
            CourseModel course = CourseModel.builder()
                .name(data.courseName())
                .institution(institution)
                .duration(data.courseDuration())
                .category(data.courseCategory())
                .frequency(data.weeklyFrequency())
                .active(true)
                .build();
            course = courseService.create(course);

            // 3. Cria o estudante
            String encodedPassword = new BCryptPasswordEncoder().encode(data.password());
            
            StudentModel newStudent = StudentModel.builder()
                .ra(data.ra())
                .name(data.name())
                .photo(data.photo())
                .email(data.email())
                .password(encodedPassword)
                .institution(institution)
                .course(course)
                .semester(data.currentSemester())
                .role(UserRole.STUDENT)
                .active(true)
                .build();

                var savedStudent = this.studentService.create(newStudent);

                var dto = new com.gmnds.academy.dto.StudentResponseDTO(
                    savedStudent.getId(),
                    savedStudent.getRa(),
                    savedStudent.getName(),
                    savedStudent.getPhoto(),
                    savedStudent.getInstitution() != null ? savedStudent.getInstitution().getName() : null,
                    savedStudent.getCourse() != null ? savedStudent.getCourse().getName() : null,
                    savedStudent.getSemester(),
                    savedStudent.getAverage_grade(),
                    savedStudent.getEmail(),
                    savedStudent.getRole(),
                    savedStudent.isActive()
                );

                return ResponseEntity.ok(dto);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao registrar estudante: " + e.getMessage());
        }
    }

    @PostMapping("register-simple")
    @Operation(summary = "Registrar estudante simples", description = "Registra um novo estudante sem instituição e curso (modo simplificado)")
    public ResponseEntity<RegisterDTO> registerSimple(@RequestBody @Valid RegisterDTO data) {

        if(this.repUser.findByEmail(data.login()) != null) return ResponseEntity.badRequest().build();

        String encodedPassword = new BCryptPasswordEncoder().encode(data.password());
        StudentModel newuser = new StudentModel(data.ra(), data.login(), encodedPassword, data.role());

        this.repUser.save(newuser);

        return ResponseEntity.ok().build();

    }
}
