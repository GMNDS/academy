package com.gmnds.academy.controllers;

import com.gmnds.academy.dto.AddInstitutionDTO;
import com.gmnds.academy.dto.UpdateInstitutionDTO;
import com.gmnds.academy.models.CourseModel;
import com.gmnds.academy.models.InstitutionModel;
import com.gmnds.academy.repositories.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/institutions")
public class InstitutionController {

    @Autowired
    InstitutionRepository repInstitution;

    @GetMapping
    public List<InstitutionModel> getAll() {
        return repInstitution.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstitutionModel> getbyId(@PathVariable Long id) {

        return repInstitution.findById(id)
                .map(institution -> ResponseEntity.ok().body(institution))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<InstitutionModel> createInstitution(@RequestBody AddInstitutionDTO data) {

        //InstitutionModel institutionModel = institutionOptional.get();

        InstitutionModel newInstitution = new InstitutionModel();
        newInstitution.setName(data.name());
        newInstitution.setAcronym(data.acronym());
        newInstitution.setType(data.type());

        InstitutionModel savedInstitution = repInstitution.save(newInstitution);
        return ResponseEntity.status(201).body(savedInstitution);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstitutionModel> updateInstitution(@PathVariable Long id, @RequestBody UpdateInstitutionDTO data) {
        Optional<InstitutionModel> inst = repInstitution.findById(id);

        if(inst.isPresent()) {
            InstitutionModel existingInstitution = inst.get();
            existingInstitution.setName(inst.get().getName());
            existingInstitution.setAcronym(inst.get().getAcronym());
            existingInstitution.setType(inst.get().getType());
            existingInstitution.setActive(inst.get().isActive());

            InstitutionModel updatedInstitution = repInstitution.save(existingInstitution);
            return ResponseEntity.ok(updatedInstitution);

        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstitution(@PathVariable Long id) {
        Optional<InstitutionModel> inst = repInstitution.findById(id);

        if (inst.isPresent()) {
           boolean active = inst.get().isActive();
           if (active) {
               inst.get().setActive(false);
               repInstitution.save(inst.get());
                return ResponseEntity.ok().build();
           }
              return ResponseEntity.notFound().build();
        }
        return ResponseEntity.notFound().build();
    }

}
