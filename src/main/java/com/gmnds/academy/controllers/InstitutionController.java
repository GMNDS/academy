package com.gmnds.academy.controllers;

import com.gmnds.academy.dto.AddInstitutionDTO;
import com.gmnds.academy.dto.InstitutionResponseDTO;
import com.gmnds.academy.dto.UpdateInstitutionDTO;
import com.gmnds.academy.models.InstitutionModel;
import com.gmnds.academy.services.InstitutionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/institutions")
@Tag(name = "Instituições", description = "Gerenciamento de instituições de ensino")
public class InstitutionController {

    @Autowired
    private InstitutionService institutionService;

    @GetMapping
    @Operation(summary = "Listar todas as instituições", description = "Retorna a lista completa de instituições cadastradas")
    public List<InstitutionResponseDTO> getAll() {
        List<InstitutionModel> institutions = institutionService.findAll();
        return institutions.stream()
                .map(institution -> new InstitutionResponseDTO(
                        institution.getId(),
                        institution.getName(),
                        institution.getAcronym(),
                        institution.getType(),
                        institution.isActive()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar instituição por ID", description = "Retorna uma instituição específica pelo ID")
    public ResponseEntity<InstitutionResponseDTO> getbyId(@PathVariable Long id) {
        try {
            InstitutionModel institution = institutionService.findById(id);
            InstitutionResponseDTO dto = new InstitutionResponseDTO(
                    institution.getId(),
                    institution.getName(),
                    institution.getAcronym(),
                    institution.getType(),
                    institution.isActive()
            );
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Criar instituição", description = "Cadastra uma nova instituição de ensino")
    public ResponseEntity<InstitutionModel> createInstitution(@RequestBody AddInstitutionDTO data) {
        InstitutionModel newInstitution = new InstitutionModel();
        newInstitution.setName(data.name());
        newInstitution.setAcronym(data.acronym());
        newInstitution.setType(data.type());

        InstitutionModel savedInstitution = institutionService.create(newInstitution);
        return ResponseEntity.status(201).body(savedInstitution);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar instituição", description = "Atualiza os dados de uma instituição existente")
    public ResponseEntity<InstitutionModel> updateInstitution(@PathVariable Long id, @RequestBody UpdateInstitutionDTO data) {
        InstitutionModel existingInstitution = institutionService.findById(id);
        
        if (data.name() != null) {
            existingInstitution.setName(data.name());
        }
        if (data.acronym() != null) {
            existingInstitution.setAcronym(data.acronym());
        }
        if (data.active() != null) {
            existingInstitution.setActive(data.active());
        }

        try {
            InstitutionModel updatedInstitution = institutionService.save(id, existingInstitution);
            return ResponseEntity.ok(updatedInstitution);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar instituição", description = "Remove uma instituição pelo ID")
    public ResponseEntity<Void> deleteInstitution(@PathVariable Long id) {
        try {
            institutionService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
