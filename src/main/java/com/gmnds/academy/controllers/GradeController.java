package com.gmnds.academy.controllers;

import com.gmnds.academy.dto.AddGradeDTO;
import com.gmnds.academy.dto.GradeResponseDTO;
import com.gmnds.academy.dto.UpdateGradeDTO;
import com.gmnds.academy.models.GradeModel;
import com.gmnds.academy.services.GradeService;
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
@RequestMapping("/grades" )
@Tag(name = "Notas finais", description = "Gerenciamento de pesos de avaliações (P1, P2, P3, T, etc)")
public class GradeController {

    @Autowired
    private GradeService gradeService;
    @Autowired
    private com.gmnds.academy.repositories.InstitutionRepository institutionRepository;

    @GetMapping
    @Operation(summary = "Listar todos os pesos", description = "Retorna a lista completa de Notas finais")
    public List<GradeResponseDTO> getAllGrades() {
        List<GradeModel> grades = gradeService.findAll();
        return grades.stream()
                .map(grade -> new GradeResponseDTO(
                        grade.getId(),
                        grade.getName(),
                        grade.getWeight(),
                        grade.getInstitution() != null ? grade.getInstitution().getId() : null,
                        grade.getInstitution() != null ? grade.getInstitution().getName() : null
                ))
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar peso por ID", description = "Retorna um peso de avaliação específico pelo ID")
    public ResponseEntity<GradeResponseDTO> getGradeById(@PathVariable Long id) {
        try {
            GradeModel grade = gradeService.findById(id);
            GradeResponseDTO dto = new GradeResponseDTO(
                    grade.getId(),
                    grade.getName(),
                    grade.getWeight(),
                    grade.getInstitution() != null ? grade.getInstitution().getId() : null,
                    grade.getInstitution() != null ? grade.getInstitution().getName() : null
            );
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Criar peso de avaliação", description = "Cadastra um novo peso de avaliação",
               requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json",
                       schema = @Schema(implementation = AddGradeDTO.class),
                       examples = @ExampleObject(value = "{\"name\":\"P1\", \"weight\":0.3, \"institutionId\":1}"))))
    public ResponseEntity<GradeModel> createGrade(@RequestBody AddGradeDTO data) {
        GradeModel newGrade = new GradeModel();
        newGrade.setName(data.name());
        newGrade.setWeight(data.weight());
        if (data.institutionId() != null) {
            var institution = institutionRepository.findById(data.institutionId()).orElseThrow(() -> new RuntimeException("Instituição não encontrada"));
            newGrade.setInstitution(institution);
        }

        try {
            GradeModel savedGrade = gradeService.create(newGrade);
            return ResponseEntity.status(201).body(savedGrade);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar peso de avaliação", description = "Atualiza um peso de avaliação existente",
               requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json",
                       schema = @Schema(implementation = UpdateGradeDTO.class),
                       examples = @ExampleObject(value = "{\"name\":\"P1\", \"weight\":0.35, \"institutionId\":1}"))))
    public ResponseEntity<GradeModel> updateGrade(@PathVariable Long id, @RequestBody UpdateGradeDTO data) {
        GradeModel newData = new GradeModel();
        newData.setName(data.name());
        newData.setWeight(data.weight());
        if (data.institutionId() != null) {
            var institution = institutionRepository.findById(data.institutionId()).orElseThrow(() -> new RuntimeException("Instituição não encontrada"));
            newData.setInstitution(institution);
        }

        try {
            GradeModel updatedGrade = gradeService.save(id, newData);
            return ResponseEntity.ok(updatedGrade);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar peso de avaliação", description = "Remove um peso de avaliação pelo ID")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        try {
            gradeService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
