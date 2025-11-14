package com.gmnds.academy.controllers;

import com.gmnds.academy.dto.AddGradeDTO;
import com.gmnds.academy.dto.UpdateGradeDTO;
import com.gmnds.academy.models.GradeModel;
import com.gmnds.academy.repositories.GradeRepository;
import com.gmnds.academy.services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/grades" )
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private GradeRepository gradeRepository;


    //@GetMapping
    //public List<GradeModel> getAllGrades() {
    //    return null;
    //}

    @GetMapping("/{id}")
    public ResponseEntity<GradeModel> getGradeById(@PathVariable Long id) {
        // Busca por ID
        return gradeRepository.findById(id)
                .map(subject -> ResponseEntity.ok().body(subject))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createGrade(@RequestBody AddGradeDTO data) {
        GradeModel newGrade = new GradeModel();
        newGrade.setName(data.name());
        newGrade.setWeight(data.weight());
        newGrade.setInstitution(data.institution());

        GradeModel savedSubject = gradeRepository.save(newGrade);
        return ResponseEntity.status(201).body(savedSubject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGrade(@PathVariable Long id, @RequestBody UpdateGradeDTO data) {
        Optional<GradeModel> gradeOptional = gradeRepository.findById(id);

        if (gradeOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        GradeModel existingGrade = gradeOptional.get();
        // Atualiza os campos no DTO
        existingGrade.setName(data.name());
        existingGrade.setWeight(data.weight());
        existingGrade.setInstitution(data.institution());

        try {
            GradeModel updatedGrade = gradeService.update(id, existingGrade);
            return ResponseEntity.ok(updatedGrade);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        try {
            gradeService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
