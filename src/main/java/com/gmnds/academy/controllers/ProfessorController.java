package com.gmnds.academy.controllers;

import com.gmnds.academy.dto.AddProfessorDTO;
import com.gmnds.academy.dto.UpdateProfessorDTO;
import com.gmnds.academy.models.GradeModel;
import com.gmnds.academy.models.ProfessorModel;
import com.gmnds.academy.repositories.ProfessorRepository;
import com.gmnds.academy.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professors" )
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private ProfessorRepository professorRepository;

    //@GetMapping
    //public List<ProfessorModel> getAllProfessors() {
    //    return null;
    //}

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorModel> getProfessorById(@PathVariable Long id) {
        return professorRepository.findById(id)
                .map(subject -> ResponseEntity.ok().body(subject))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createProfessor(@RequestBody AddProfessorDTO data) {
        ProfessorModel newProfessor = new ProfessorModel();
        newProfessor.setName(data.name());

        ProfessorModel savedSubject = professorRepository.save(newProfessor);
        return ResponseEntity.status(201).body(savedSubject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProfessor(@PathVariable Long id, @RequestBody UpdateProfessorDTO data) {
        Optional<ProfessorModel> professorOptional = professorRepository.findById(id);

        if (professorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ProfessorModel existingProfessor = professorOptional.get();
        // Atualiza os campos no DTO
        existingProfessor.setName(data.name());

        try {
            ProfessorModel updatedProfessor = professorService.update(id, existingProfessor);
            return ResponseEntity.ok(updatedProfessor);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
        try {
            professorService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
    }
}
