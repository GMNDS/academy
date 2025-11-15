package com.gmnds.academy.controllers;

import com.gmnds.academy.dto.AbsenceResponseDTO;
import com.gmnds.academy.models.AbsenceModel;
import com.gmnds.academy.services.AbsenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/absences" )
@Tag(name = "Faltas/Frequência", description = "Gerenciamento de faltas e frequência dos estudantes")
public class AbsenceController {

    @Autowired
    private AbsenceService absenceService;


    @GetMapping
    @Operation(summary = "Listar todas as frequências", description = "Retorna a lista completa de registros de frequência")
    public List<AbsenceResponseDTO> getAllAbsences() {
        List<AbsenceModel> absences = absenceService.findAll();
        return absences.stream()
                .map(absence -> new AbsenceResponseDTO(
                        absence.getId(),
                        absence.getStudent() != null ? absence.getStudent().getId() : null,
                        absence.getStudent() != null ? absence.getStudent().getName() : null,
                        absence.getSubject() != null ? absence.getSubject().getId() : null,
                        absence.getSubject() != null ? absence.getSubject().getName() : null,
                        absence.getTotalClasses(),
                        absence.getAttendances(),
                        absence.getPercentage()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar frequência por ID", description = "Retorna um registro de frequência específico pelo ID")
    public ResponseEntity<AbsenceResponseDTO> getAbsenceById(@PathVariable Long id) {
        try {
            AbsenceModel absence = absenceService.findById(id);
            AbsenceResponseDTO dto = new AbsenceResponseDTO(
                    absence.getId(),
                    absence.getStudent() != null ? absence.getStudent().getId() : null,
                    absence.getStudent() != null ? absence.getStudent().getName() : null,
                    absence.getSubject() != null ? absence.getSubject().getId() : null,
                    absence.getSubject() != null ? absence.getSubject().getName() : null,
                    absence.getTotalClasses(),
                    absence.getAttendances(),
                    absence.getPercentage()
            );
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Criar registro de frequência", description = "Cria um novo registro de frequência para um estudante em uma disciplina")
    public ResponseEntity<AbsenceModel> createAbsence(@RequestBody AbsenceModel data) {
        try {
            AbsenceModel savedAbsence = absenceService.create(data);
            return ResponseEntity.status(201).body(savedAbsence);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar registro de frequência", description = "Atualiza um registro de frequência existente")
    public ResponseEntity<AbsenceModel> updateAbsence(@PathVariable Long id, @RequestBody AbsenceModel data) {
        try {
            AbsenceModel updatedAbsence = absenceService.save(id, data);
            return ResponseEntity.ok(updatedAbsence);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar registro de frequência", description = "Remove um registro de frequência pelo ID")
    public ResponseEntity<Void> deleteAbsence(@PathVariable Long id) {
        try {
            absenceService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
