package com.gmnds.academy.controllers;

import com.gmnds.academy.dto.*;
import com.gmnds.academy.models.ExamModel;
import com.gmnds.academy.repositories.ExamRepository;
import com.gmnds.academy.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exams")
public class ExamController {

    @Autowired
    private ExamService examService; // Injeção do Service

    @Autowired
    private ExamRepository examRepository;

    @GetMapping("/{id}")
    public ResponseEntity<ExamModel> getExamById(@PathVariable Long id) {
        // Busca por ID
        return examRepository.findById(id)
                .map(exam -> ResponseEntity.ok().body(exam))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ExamModel> createExam(@RequestBody AddExamDTO data) {

        ExamModel newExam = new ExamModel();
        newExam.setSubject(data.subject());
        newExam.setExamDate(data.exam_date());
        newExam.setType(data.type());

        ExamModel savedExam = examRepository.save(newExam);
        return ResponseEntity.status(201).body(savedExam);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateExam(@PathVariable Long id, @RequestBody UpdateExamDTO data) {

        Optional<ExamModel> examOptional = examRepository.findById(id);

        if (examOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ExamModel existingExam = examOptional.get();
        // Atualiza os campos no DTO
        existingExam.setSubject(data.subject());
        existingExam.setExamDate(data.exam_date());
        existingExam.setType(data.type());

        try {
            ExamModel updatedExam = examService.update(id, existingExam);
            return ResponseEntity.ok(updatedExam);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        try {
            examService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
