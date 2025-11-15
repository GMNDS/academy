package com.gmnds.academy.controllers;

import com.gmnds.academy.dto.AddStudentGradeDTO;
import com.gmnds.academy.dto.StudentGradeResponseDTO;
import com.gmnds.academy.dto.UpdateStudentGradeDTO;
import com.gmnds.academy.models.StudentGradeModel;
import com.gmnds.academy.services.StudentGradeService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Criar nota", description = "Registra uma nova nota para um estudante em uma disciplina")
    public ResponseEntity<StudentGradeModel> createStudentGrade(@RequestBody AddStudentGradeDTO data) {
        StudentGradeModel newStudentGrade = new StudentGradeModel();
        newStudentGrade.setStudent(data.student());
        newStudentGrade.setGrade(data.grade());
        newStudentGrade.setSubject(data.subject());
        newStudentGrade.setScore(data.score());

        try {
            StudentGradeModel savedStudentGrade = studentGradeService.create(newStudentGrade);
            return ResponseEntity.status(201).body(savedStudentGrade);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar nota", description = "Atualiza uma nota existente")
    public ResponseEntity<StudentGradeModel> updateStudentGrade(@PathVariable Long id, @RequestBody UpdateStudentGradeDTO data) {
        StudentGradeModel newData = new StudentGradeModel();
        newData.setStudent(data.student());
        newData.setGrade(data.grade());
        newData.setSubject(data.subject());
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
