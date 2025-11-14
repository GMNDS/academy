package com.gmnds.academy.controllers;

import com.gmnds.academy.dto.AddSubjectDTO;
import com.gmnds.academy.dto.UpdateSubjectDTO;
import com.gmnds.academy.models.SubjectModel;
import com.gmnds.academy.repositories.SubjectRepository;
import com.gmnds.academy.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subjects" )
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectRepository subjectRepository;


    //@GetMapping
    //public List<SubjectModel> getAllSubjects() {
    //
    //    return null;
    //}

    @GetMapping("/{id}")
    public ResponseEntity<SubjectModel> getSubjectById(@PathVariable Long id) {
        // Busca por ID
        return subjectRepository.findById(id)
                .map(subject -> ResponseEntity.ok().body(subject))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SubjectModel> createSubject(@RequestBody AddSubjectDTO data) {

        SubjectModel newSubject = new SubjectModel();
        newSubject.setName(data.name());
        newSubject.setCourse(data.course());
        newSubject.setCode(data.code());
        newSubject.setTotalClasses(data.totalClasses());
        newSubject.setProfessor(data.professor());

        SubjectModel savedSubject = subjectRepository.save(newSubject);
        return ResponseEntity.status(201).body(savedSubject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubject(@PathVariable Long id, @RequestBody UpdateSubjectDTO data) {
        Optional<SubjectModel> subjectOptional = subjectRepository.findById(id);

        if (subjectOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        SubjectModel existingSubject = subjectOptional.get();
        // Atualiza os campos no DTO
        existingSubject.setName(data.name());
        existingSubject.setCourse(data.course());
        existingSubject.setCode(data.code());
        existingSubject.setTotalClasses(data.totalClasses());
        existingSubject.setProfessor(data.professor());

        try {
            SubjectModel updatedSubject = subjectService.update(id, existingSubject);
            return ResponseEntity.ok(updatedSubject);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        try {
            subjectService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
