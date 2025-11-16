package com.gmnds.academy.controllers;

import com.gmnds.academy.dto.AddAbsenceDTO;
import com.gmnds.academy.dto.AbsenceResponseDTO;
import com.gmnds.academy.dto.UpdateAbsenceDTO;
import com.gmnds.academy.models.AbsenceModel;
import com.gmnds.academy.repositories.StudentRepository;
import com.gmnds.academy.repositories.SubjectRepository;
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

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;


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
    @Operation(summary = "Criar registro de frequência", description = "Cria um novo registro de frequência para um estudante em uma disciplina",
               requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                       schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = AddAbsenceDTO.class))))
    public ResponseEntity<AbsenceResponseDTO> createAbsence(@RequestBody AddAbsenceDTO data) {
        try {
            // Buscar estudante
            var student = studentRepository.findById(data.studentId())
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado"));

            // Buscar disciplina
            var subject = subjectRepository.findById(data.subjectId())
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

            // Criar registro de frequência
            AbsenceModel newAbsence = AbsenceModel.builder()
                .student(student)
                .subject(subject)
                .totalClasses(data.totalClasses())
                .attendances(data.attendances())
                .build();

            AbsenceModel savedAbsence = absenceService.create(newAbsence);

            AbsenceResponseDTO response = new AbsenceResponseDTO(
                savedAbsence.getId(),
                savedAbsence.getStudent().getId(),
                savedAbsence.getStudent().getName(),
                savedAbsence.getSubject().getId(),
                savedAbsence.getSubject().getName(),
                savedAbsence.getTotalClasses(),
                savedAbsence.getAttendances(),
                savedAbsence.getPercentage()
            );

            return ResponseEntity.status(201).body(response);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar registro de frequência", description = "Atualiza um registro de frequência existente",
               requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                       schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UpdateAbsenceDTO.class))))
    public ResponseEntity<AbsenceResponseDTO> updateAbsence(@PathVariable Long id, @RequestBody UpdateAbsenceDTO data) {
        try {
            AbsenceModel existingAbsence = absenceService.findById(id);

            // Atualizar estudante se fornecido
            if (data.studentId() != null) {
                var student = studentRepository.findById(data.studentId())
                    .orElseThrow(() -> new RuntimeException("Estudante não encontrado"));
                existingAbsence.setStudent(student);
            }

            // Atualizar disciplina se fornecida
            if (data.subjectId() != null) {
                var subject = subjectRepository.findById(data.subjectId())
                    .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
                existingAbsence.setSubject(subject);
            }

            // Atualizar outros campos se fornecidos
            if (data.totalClasses() != null) {
                existingAbsence.setTotalClasses(data.totalClasses());
            }
            if (data.attendances() != null) {
                existingAbsence.setAttendances(data.attendances());
            }

            AbsenceModel updatedAbsence = absenceService.save(id, existingAbsence);

            AbsenceResponseDTO response = new AbsenceResponseDTO(
                updatedAbsence.getId(),
                updatedAbsence.getStudent().getId(),
                updatedAbsence.getStudent().getName(),
                updatedAbsence.getSubject().getId(),
                updatedAbsence.getSubject().getName(),
                updatedAbsence.getTotalClasses(),
                updatedAbsence.getAttendances(),
                updatedAbsence.getPercentage()
            );

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
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
