package com.gmnds.academy.controllers;

import com.gmnds.academy.dto.AddStudentGradeDTO;
import com.gmnds.academy.dto.StudentGradeResponseDTO;
import com.gmnds.academy.dto.UpdateStudentGradeDTO;
import com.gmnds.academy.models.StudentGradeModel;
import com.gmnds.academy.services.StudentGradeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studentgrades" )
@Tag(name = "Notas dos Estudantes", description = "Gerenciamento de notas dos estudantes")
public class StudentGradeController {

    @Autowired
    private StudentGradeService studentGradeService;
    @Autowired
    private com.gmnds.academy.repositories.StudentRepository studentRepository;
    @Autowired
    private com.gmnds.academy.repositories.GradeRepository gradeRepository;
    @Autowired
    private com.gmnds.academy.repositories.SubjectRepository subjectRepository;

    @GetMapping
    @Operation(summary = "Listar todas as notas", description = "Retorna a lista completa de notas dos estudantes")
    public List<StudentGradeResponseDTO> getAllStudentGrades() {
        List<StudentGradeModel> studentGrades = studentGradeService.findAll();
        return studentGrades.stream()
                .map(sg -> new StudentGradeResponseDTO(
                        sg.getId(),
                        sg.getStudent() != null ? sg.getStudent().getId() : null,
                        sg.getStudent() != null ? sg.getStudent().getName() : null,
                        sg.getGrade() != null ? sg.getGrade().getId() : null,
                        sg.getGrade() != null ? sg.getGrade().getName() : null,
                        sg.getGrade() != null ? sg.getGrade().getWeight() : null,
                        sg.getSubject() != null ? sg.getSubject().getId() : null,
                        sg.getSubject() != null ? sg.getSubject().getName() : null,
                        sg.getScore()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar nota por ID", description = "Retorna uma nota específica pelo ID")
    public ResponseEntity<StudentGradeResponseDTO> getStudentGradeById(@PathVariable Long id) {
        try {
            StudentGradeModel sg = studentGradeService.findById(id);
            StudentGradeResponseDTO dto = new StudentGradeResponseDTO(
                    sg.getId(),
                    sg.getStudent() != null ? sg.getStudent().getId() : null,
                    sg.getStudent() != null ? sg.getStudent().getName() : null,
                    sg.getGrade() != null ? sg.getGrade().getId() : null,
                    sg.getGrade() != null ? sg.getGrade().getName() : null,
                    sg.getGrade() != null ? sg.getGrade().getWeight() : null,
                    sg.getSubject() != null ? sg.getSubject().getId() : null,
                    sg.getSubject() != null ? sg.getSubject().getName() : null,
                    sg.getScore()
            );
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Criar nota", description = "Registra uma nova nota para um estudante em uma disciplina",
               requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json",
                       schema = @Schema(implementation = AddStudentGradeDTO.class),
                       examples = @ExampleObject(value = "{\"studentId\":1, \"gradeId\":2, \"subjectId\":3, \"score\":8.5}"))))
    public ResponseEntity<StudentGradeModel> createStudentGrade(@RequestBody AddStudentGradeDTO data) {
        StudentGradeModel newStudentGrade = new StudentGradeModel();
        if (data.studentId() != null) {
            var student = studentRepository.findById(data.studentId()).orElseThrow(() -> new RuntimeException("Estudante não encontrado"));
            newStudentGrade.setStudent(student);
        }
        if (data.gradeId() != null) {
            var grade = gradeRepository.findById(data.gradeId()).orElseThrow(() -> new RuntimeException("Peso de avaliação não encontrado"));
            newStudentGrade.setGrade(grade);
        }
        if (data.subjectId() != null) {
            var subject = subjectRepository.findById(data.subjectId()).orElseThrow(() -> new RuntimeException("Matéria não encontrada"));
            newStudentGrade.setSubject(subject);
        }
        newStudentGrade.setScore(data.score());

        try {
            StudentGradeModel savedStudentGrade = studentGradeService.create(newStudentGrade);
            return ResponseEntity.status(201).body(savedStudentGrade);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar nota", description = "Atualiza uma nota existente",
               requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json",
                       schema = @Schema(implementation = UpdateStudentGradeDTO.class),
                       examples = @ExampleObject(value = "{\"studentId\":1, \"gradeId\":2, \"subjectId\":3, \"score\":7.0}"))))
    public ResponseEntity<StudentGradeModel> updateStudentGrade(@PathVariable Long id, @RequestBody UpdateStudentGradeDTO data) {
        StudentGradeModel newData = new StudentGradeModel();
        if (data.studentId() != null) {
            var student = studentRepository.findById(data.studentId()).orElseThrow(() -> new RuntimeException("Estudante não encontrado"));
            newData.setStudent(student);
        }
        if (data.gradeId() != null) {
            var grade = gradeRepository.findById(data.gradeId()).orElseThrow(() -> new RuntimeException("Peso de avaliação não encontrado"));
            newData.setGrade(grade);
        }
        if (data.subjectId() != null) {
            var subject = subjectRepository.findById(data.subjectId()).orElseThrow(() -> new RuntimeException("Matéria não encontrada"));
            newData.setSubject(subject);
        }
        newData.setScore(data.score());

        try {
            StudentGradeModel updatedStudentGrade = studentGradeService.save(id, newData);
            return ResponseEntity.ok(updatedStudentGrade);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar nota", description = "Remove uma nota pelo ID")
    public ResponseEntity<Void> deleteStudentGrade(@PathVariable Long id) {
        try {
            studentGradeService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
