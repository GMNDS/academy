package com.gmnds.academy.controllers;

import com.gmnds.academy.dto.AddSubjectDTO;
import com.gmnds.academy.dto.SubjectResponseDTO;
import com.gmnds.academy.dto.UpdateSubjectDTO;
import com.gmnds.academy.models.SubjectModel;
import com.gmnds.academy.services.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Autowired
    private com.gmnds.academy.repositories.CourseRepository courseRepository;
    @Autowired
    private com.gmnds.academy.repositories.ProfessorRepository professorRepository;

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
    @Operation(summary = "Criar disciplina", description = "Cadastra uma nova disciplina vinculada a um curso",
               requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json",
                       schema = @Schema(implementation = AddSubjectDTO.class),
                       examples = @ExampleObject(value = "{\"name\":\"Matemática\", \"courseId\":2, \"code\":\"MAT101\", \"totalClasses\":80, \"professorId\":1}"))))
    public ResponseEntity<SubjectModel> createSubject(@RequestBody AddSubjectDTO data) {
        SubjectModel newSubject = new SubjectModel();
        newSubject.setName(data.name());
        if (data.courseId() != null) {
            var course = courseRepository.findById(data.courseId()).orElseThrow(() -> new RuntimeException("Curso não encontrado"));
            newSubject.setCourse(course);
        }
        newSubject.setCode(data.code());
        newSubject.setTotalClasses(data.totalClasses());
        if (data.professorId() != null) {
            var professor = professorRepository.findById(data.professorId()).orElseThrow(() -> new RuntimeException("Professor não encontrado"));
            newSubject.setProfessor(professor);
        }

        try {
            SubjectModel savedSubject = subjectService.create(newSubject);
            return ResponseEntity.status(201).body(savedSubject);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar disciplina", description = "Atualiza os dados de uma disciplina existente",
               requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json",
                       schema = @Schema(implementation = UpdateSubjectDTO.class),
                       examples = @ExampleObject(value = "{\"name\":\"Matemática II\", \"courseId\":2, \"code\":\"MAT102\", \"totalClasses\":80, \"professorId\":1}"))))
    public ResponseEntity<SubjectModel> updateSubject(@PathVariable Long id, @RequestBody UpdateSubjectDTO data) {
        SubjectModel newData = new SubjectModel();
        newData.setName(data.name());
        if (data.courseId() != null) {
            var course = courseRepository.findById(data.courseId()).orElseThrow(() -> new RuntimeException("Curso não encontrado"));
            newData.setCourse(course);
        }
        newData.setCode(data.code());
        newData.setTotalClasses(data.totalClasses());
        if (data.professorId() != null) {
            var professor = professorRepository.findById(data.professorId()).orElseThrow(() -> new RuntimeException("Professor não encontrado"));
            newData.setProfessor(professor);
        }

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
