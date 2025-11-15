package com.gmnds.academy.controllers;

import com.gmnds.academy.dto.AddSubjectDTO;
import com.gmnds.academy.dto.SubjectResponseDTO;
import com.gmnds.academy.dto.UpdateSubjectDTO;
import com.gmnds.academy.models.SubjectModel;
import com.gmnds.academy.services.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects" )
@Tag(name = "Disciplinas", description = "Gerenciamento de disciplinas")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    @Operation(summary = "Listar todas as disciplinas", description = "Retorna a lista completa de disciplinas cadastradas")
    public List<SubjectResponseDTO> getAllSubjects() {
        List<SubjectModel> subjects = subjectService.findAll();
        return subjects.stream()
                .map(subject -> new SubjectResponseDTO(
                        subject.getId(),
                        subject.getName(),
                        subject.getCourse() != null ? subject.getCourse().getId() : null,
                        subject.getCourse() != null ? subject.getCourse().getName() : null,
                        subject.getCode(),
                        subject.getTotalClasses(),
                        subject.getProfessor() != null ? subject.getProfessor().getId() : null,
                        subject.getProfessor() != null ? subject.getProfessor().getName() : null
                ))
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar disciplina por ID", description = "Retorna uma disciplina específica pelo ID")
    public ResponseEntity<SubjectResponseDTO> getSubjectById(@PathVariable Long id) {
        try {
            SubjectModel subject = subjectService.findById(id);
            SubjectResponseDTO dto = new SubjectResponseDTO(
                    subject.getId(),
                    subject.getName(),
                    subject.getCourse() != null ? subject.getCourse().getId() : null,
                    subject.getCourse() != null ? subject.getCourse().getName() : null,
                    subject.getCode(),
                    subject.getTotalClasses(),
                    subject.getProfessor() != null ? subject.getProfessor().getId() : null,
                    subject.getProfessor() != null ? subject.getProfessor().getName() : null
            );
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Criar disciplina", description = "Cadastra uma nova disciplina vinculada a um curso")
    public ResponseEntity<SubjectModel> createSubject(@RequestBody AddSubjectDTO data) {
        SubjectModel newSubject = new SubjectModel();
        newSubject.setName(data.name());
        newSubject.setCourse(data.course());
        newSubject.setCode(data.code());
        newSubject.setTotalClasses(data.totalClasses());
        newSubject.setProfessor(data.professor());

        try {
            SubjectModel savedSubject = subjectService.create(newSubject);
            return ResponseEntity.status(201).body(savedSubject);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar disciplina", description = "Atualiza os dados de uma disciplina existente")
    public ResponseEntity<SubjectModel> updateSubject(@PathVariable Long id, @RequestBody UpdateSubjectDTO data) {
        SubjectModel newData = new SubjectModel();
        newData.setName(data.name());
        newData.setCourse(data.course());
        newData.setCode(data.code());
        newData.setTotalClasses(data.totalClasses());
        newData.setProfessor(data.professor());

        try {
            SubjectModel updatedSubject = subjectService.save(id, newData);
            return ResponseEntity.ok(updatedSubject);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar disciplina", description = "Remove uma disciplina pelo ID")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        try {
            subjectService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
