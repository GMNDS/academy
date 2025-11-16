package com.gmnds.academy.controllers;

import com.gmnds.academy.dto.SubjectCreateDTO;
import com.gmnds.academy.dto.SubjectResponseDTO;
import com.gmnds.academy.dto.UpdateSubjectDTO;
import com.gmnds.academy.models.SubjectModel;
import com.gmnds.academy.services.SubjectService;
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
    @Operation(summary = "Criar disciplina", description = "Cadastra uma nova disciplina vinculada a um curso. O professor será criado automaticamente a partir do nome fornecido.")
    public ResponseEntity<SubjectResponseDTO> createSubject(@RequestBody SubjectCreateDTO data) {
        try {
            // Log para debug
            System.out.println("Dados recebidos: name=" + data.name() + ", courseId=" + data.courseId() + 
                             ", code=" + data.code() + ", totalClasses=" + data.totalClasses() + 
                             ", professorName=" + data.professorName());
            
            // Validar campos obrigatórios
            if (data.courseId() == null) {
                throw new RuntimeException("O ID do curso é obrigatório");
            }
            if (data.name() == null || data.name().trim().isEmpty()) {
                throw new RuntimeException("O nome da disciplina é obrigatório");
            }
            
            // Buscar o curso
            var course = courseRepository.findById(data.courseId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com ID: " + data.courseId()));
            
            // Criar professor se o nome for fornecido
            com.gmnds.academy.models.ProfessorModel professor = null;
            if (data.professorName() != null && !data.professorName().trim().isEmpty()) {
                professor = com.gmnds.academy.models.ProfessorModel.builder()
                    .name(data.professorName().trim())
                    .build();
                professor = professorRepository.save(professor);
            }
            
            // Criar a disciplina
            SubjectModel newSubject = SubjectModel.builder()
                .name(data.name())
                .course(course)
                .code(data.code())
                .totalClasses(data.totalClasses())
                .professor(professor)
                .build();
            
            SubjectModel savedSubject = subjectService.create(newSubject);
            
            SubjectResponseDTO response = new SubjectResponseDTO(
                savedSubject.getId(),
                savedSubject.getName(),
                savedSubject.getCourse().getId(),
                savedSubject.getCourse().getName(),
                savedSubject.getCode(),
                savedSubject.getTotalClasses(),
                savedSubject.getProfessor() != null ? savedSubject.getProfessor().getId() : null,
                savedSubject.getProfessor() != null ? savedSubject.getProfessor().getName() : null
            );
            
            return ResponseEntity.status(201).body(response);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar disciplina: " + e.getMessage());
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
