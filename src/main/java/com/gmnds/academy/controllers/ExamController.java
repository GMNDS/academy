package com.gmnds.academy.controllers;

import com.gmnds.academy.dto.*;
import com.gmnds.academy.models.ExamModel;
import com.gmnds.academy.services.ExamService;
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
@RequestMapping("/exams")
@Tag(name = "Provas", description = "Gerenciamento de provas e avaliações")
public class ExamController {

    @Autowired
    private ExamService examService;
    @Autowired
    private com.gmnds.academy.repositories.SubjectRepository subjectRepository;

    @GetMapping
    @Operation(summary = "Listar todas as provas", description = "Retorna a lista completa de provas cadastradas")
    public List<ExamResponseDTO> getAllExams() {
        List<ExamModel> exams = examService.findAll();
        return exams.stream()
                .map(exam -> new ExamResponseDTO(
                        exam.getId(),
                        exam.getSubject() != null ? exam.getSubject().getId() : null,
                        exam.getSubject() != null ? exam.getSubject().getName() : null,
                        exam.getSubject() != null ? exam.getSubject().getCode() : null,
                        exam.getExamDate(),
                        exam.getType()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar prova por ID", description = "Retorna uma prova específica pelo ID")
    public ResponseEntity<ExamResponseDTO> getExamById(@PathVariable Long id) {
        try {
            ExamModel exam = examService.findById(id);
            ExamResponseDTO dto = new ExamResponseDTO(
                    exam.getId(),
                    exam.getSubject() != null ? exam.getSubject().getId() : null,
                    exam.getSubject() != null ? exam.getSubject().getName() : null,
                    exam.getSubject() != null ? exam.getSubject().getCode() : null,
                    exam.getExamDate(),
                    exam.getType()
            );
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Criar prova", description = "Cadastra uma nova prova para uma disciplina",
               requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json",
                       schema = @Schema(implementation = AddExamDTO.class),
                       examples = @ExampleObject(value = "{\"subjectId\":2, \"exam_date\":\"2025-12-01\", \"type\":\"P1\"}"))))
    public ResponseEntity<ExamModel> createExam(@RequestBody AddExamDTO data) {
        ExamModel newExam = new ExamModel();
        if (data.subjectId() != null) {
            var subject = subjectRepository.findById(data.subjectId()).orElseThrow(() -> new RuntimeException("Matéria não encontrada"));
            newExam.setSubject(subject);
        }
        newExam.setExamDate(data.exam_date());
        newExam.setType(data.type());

        try {
            ExamModel savedExam = examService.create(newExam);
            return ResponseEntity.status(201).body(savedExam);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("/{id}")
    @Operation(summary = "Atualizar prova", description = "Atualiza os dados de uma prova existente",
               requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json",
                       schema = @Schema(implementation = UpdateExamDTO.class),
                       examples = @ExampleObject(value = "{\"subjectId\":2, \"exam_date\":\"2025-12-05\", \"type\":\"P2\"}"))))
    public ResponseEntity<ExamModel> updateExam(@PathVariable Long id, @RequestBody UpdateExamDTO data) {
        ExamModel newData = new ExamModel();
        if (data.subjectId() != null) {
            var subject = subjectRepository.findById(data.subjectId()).orElseThrow(() -> new RuntimeException("Matéria não encontrada"));
            newData.setSubject(subject);
        }
        newData.setExamDate(data.exam_date());
        newData.setType(data.type());

        try {
            ExamModel updatedExam = examService.save(id, newData);
            return ResponseEntity.ok(updatedExam);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar prova", description = "Remove uma prova pelo ID")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        try {
            examService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
