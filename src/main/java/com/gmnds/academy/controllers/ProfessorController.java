package com.gmnds.academy.controllers;

import com.gmnds.academy.dto.AddProfessorDTO;
import com.gmnds.academy.dto.ProfessorResponseDTO;
import com.gmnds.academy.dto.UpdateProfessorDTO;
import com.gmnds.academy.models.ProfessorModel;
import com.gmnds.academy.services.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professors" )
@Tag(name = "Professores", description = "Gerenciamento de professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    @Operation(summary = "Listar todos os professores", description = "Retorna a lista completa de professores cadastrados")
    public List<ProfessorResponseDTO> getAllProfessors() {
        List<ProfessorModel> professors = professorService.findAll();
        return professors.stream()
                .map(professor -> new ProfessorResponseDTO(
                        professor.getId(),
                        professor.getName()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar professor por ID", description = "Retorna um professor específico pelo ID")
    public ResponseEntity<ProfessorResponseDTO> getProfessorById(@PathVariable Long id) {
        try {
            ProfessorModel professor = professorService.findById(id);
            ProfessorResponseDTO dto = new ProfessorResponseDTO(
                    professor.getId(),
                    professor.getName()
            );
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Criar professor", description = "Cadastra um novo professor")
    public ResponseEntity<ProfessorModel> createProfessor(@RequestBody AddProfessorDTO data) {
        ProfessorModel newProfessor = new ProfessorModel();
        newProfessor.setName(data.name());

        ProfessorModel savedProfessor = professorService.create(newProfessor);
        return ResponseEntity.status(201).body(savedProfessor);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar professor", description = "Atualiza os dados de um professor existente")
    public ResponseEntity<ProfessorModel> updateProfessor(@PathVariable Long id, @RequestBody UpdateProfessorDTO data) {
        ProfessorModel newData = new ProfessorModel();
        newData.setName(data.name());

        try {
            ProfessorModel updatedProfessor = professorService.save(id, newData);
            return ResponseEntity.ok(updatedProfessor);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar professor", description = "Remove um professor pelo ID")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
        try {
            professorService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
