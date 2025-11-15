package com.gmnds.academy.controllers;

import com.gmnds.academy.dto.StudentResponseDTO;
import com.gmnds.academy.infra.security.TokenService;
import com.gmnds.academy.models.StudentModel;
import com.gmnds.academy.services.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@Tag(name = "Estudantes", description = "Gerenciamento de estudantes")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private TokenService tokenService;

    @GetMapping
    @Operation(summary = "Listar todos os estudantes", description = "Retorna a lista completa de estudantes cadastrados")
    public List<StudentResponseDTO> getAllStudents() {
        List<StudentModel> students = studentService.findAll();
        return students.stream()
                .map(student -> new StudentResponseDTO(
                        student.getId(),
                        student.getRa(),
                        student.getName(),
                        student.getPhoto(),
                        student.getInstitution() != null ? student.getInstitution().getName() : null,
                        student.getCourse() != null ? student.getCourse().getName() : null,
                        student.getSemester(),
                        student.getAverage_grade(),
                        student.getEmail(),
                        student.getRole(),
                        student.isActive()
                ))
                .toList();
    }

    private boolean validateStudentAcess(Long id, String authorization) {

        String token = authorization.replace("Bearer ", "");
        Long authenticatedStudentId = tokenService.getUserId(token);

        return id.equals(authenticatedStudentId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar estudante por ID", description = "Retorna um estudante específico pelo ID (requer autenticação)")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable  Long id, @RequestHeader("Authorization") String authorization) {
        if (!validateStudentAcess(id, authorization)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        try {
            StudentModel student = studentService.findById(id);
            StudentResponseDTO dto = new StudentResponseDTO(
                    student.getId(),
                    student.getRa(),
                    student.getName(),
                    student.getPhoto(),
                    student.getInstitution() != null ? student.getInstitution().getName() : null,
                    student.getCourse() != null ? student.getCourse().getName() : null,
                    student.getSemester(),
                    student.getAverage_grade(),
                    student.getEmail(),
                    student.getRole(),
                    student.isActive()
            );
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar estudante", description = "Atualiza os dados de um estudante (requer autenticação)")
    public ResponseEntity<StudentModel> updateStudent(@PathVariable Long id, @RequestBody StudentModel student, @RequestHeader("Authorization") String authorization) {
        if (!validateStudentAcess(id, authorization)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        try {
            StudentModel updatedStudent = studentService.save(id, student);
            return ResponseEntity.ok(updatedStudent);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    @Operation(summary = "Desativar estudante", description = "Desativa a conta do estudante autenticado")
    public ResponseEntity<Void> deleteStudent(@RequestHeader("Authorization") String authorization) {
        String token = authorization.replace("Bearer ", "");
        Long authenticatedStudentId = tokenService.getUserId(token);

        try {
            StudentModel student = studentService.findById(authenticatedStudentId);
            if (student.isActive()) {
                student.setActive(false);
                studentService.save(authenticatedStudentId, student);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
