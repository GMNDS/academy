package com.gmnds.academy.controllers;

import com.gmnds.academy.models.AbsenceModel;
import com.gmnds.academy.services.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/absences" )
public class AbsenceController {

    @Autowired
    private AbsenceService absenceService;


    @GetMapping
    public List<AbsenceModel> getAllAbsences() {
    return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AbsenceModel> getAbsenceById(@PathVariable Long id) {
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createAbsence(@RequestBody Object data) {
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAbsence(@PathVariable Long id, @RequestBody Object data) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAbsence(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
