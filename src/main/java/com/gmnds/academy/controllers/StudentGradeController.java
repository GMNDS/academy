package com.gmnds.academy.controllers;

import com.gmnds.academy.dto.AddStudentGradeDTO;
import com.gmnds.academy.dto.UpdateStudentGradeDTO;
import com.gmnds.academy.models.ProfessorModel;
import com.gmnds.academy.models.StudentGradeModel;
import com.gmnds.academy.repositories.StudentGradeRepository;
import com.gmnds.academy.services.StudentGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/studentgrades" )
public class StudentGradeController {

    @Autowired
    private StudentGradeService studentGradeService;

    @Autowired
    private StudentGradeRepository studentGradeRepository;

    //@GetMapping
    //public List<StudentGradeModel> getAllStudentGrades() {
    //    return null;
    //}

    @GetMapping("/{id}")
    public ResponseEntity<StudentGradeModel> getStudentGradeById(@PathVariable Long id) {
            return StudentGradeRepository.findById(id)
                    .map(subject -> ResponseEntity.ok().body(subject))
                    .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createStudentGrade(@RequestBody AddStudentGradeDTO data) {
        StudentGradeModel newStudentGrade = new StudentGradeModel();

        newStudentGrade.setStudent(data.student());
        newStudentGrade.setGrade(data.grade());
        newStudentGrade.setSubject(data.subject());
        newStudentGrade.setScore(data.score());

        StudentGradeModel savedStudentGrade = studentGradeRepository.save(newStudentGrade);
        return ResponseEntity.status(201).body(savedStudentGrade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudentGrade(@PathVariable Long id, @RequestBody UpdateStudentGradeDTO data) {
        Optional<StudentGradeModel> studentGradeOptional = studentGradeRepository.findById(id);

        if (studentGradeOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        StudentGradeModel existingStudentGrade = studentGradeOptional.get();
        // Atualiza os campos no DTO
        existingStudentGrade.setStudent(data.student());
        existingStudentGrade.setGrade(data.grade());
        existingStudentGrade.setSubject(data.subject());
        existingStudentGrade.setScore(data.score());


        try {
            StudentGradeModel updatedStudentGrade = studentGradeService.update(id, existingStudentGrade);
            return ResponseEntity.ok(updatedStudentGrade);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentGrade(@PathVariable Long id) {
        try {
            studentGradeService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
