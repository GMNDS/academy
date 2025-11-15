package com.gmnds.academy.controllers;

import com.gmnds.academy.dto.AddCourseDTO;
import com.gmnds.academy.dto.CourseResponseDTO;
import com.gmnds.academy.dto.UpdateCourseDTO;
import com.gmnds.academy.models.CourseModel;
import com.gmnds.academy.models.InstitutionModel;
import com.gmnds.academy.repositories.InstitutionRepository;
import com.gmnds.academy.services.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
@Tag(name = "Cursos", description = "Gerenciamento de cursos")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private InstitutionRepository institutionRepository;

    @GetMapping
    @Operation(summary = "Listar todos os cursos", description = "Retorna a lista completa de cursos cadastrados")
    public List<CourseResponseDTO> getAllCourses() {
        List<CourseModel> courses = courseService.findAll();
        return courses.stream()
                .map(course -> new CourseResponseDTO(
                        course.getId(),
                        course.getName(),
                        course.getInstitution().getName(),
                        course.getDuration(),
                        course.getCategory(),
                        course.getFrequency(),
                        course.isActive()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar curso por ID", description = "Retorna um curso específico pelo ID")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable Long id) {
        try {
            CourseModel course = courseService.findById(id);
            CourseResponseDTO dto = new CourseResponseDTO(
                    course.getId(),
                    course.getName(),
                    course.getInstitution().getName(),
                    course.getDuration(),
                    course.getCategory(),
                    course.getFrequency(),
                    course.isActive()
            );
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Criar curso", description = "Cadastra um novo curso vinculado a uma instituição")
    public ResponseEntity<?> createCourse(@RequestBody AddCourseDTO data) {

        Optional<InstitutionModel> institutionOptional = institutionRepository.findByNameIgnoreCase(data.institution());

        if (institutionOptional.isEmpty()) {
            return ResponseEntity.status(404).body("Instituição não encontrada com o nome: " + data.institution());
        }

        InstitutionModel institutionModel = institutionOptional.get();

        CourseModel newCourse = new CourseModel();
        newCourse.setName(data.name());
        newCourse.setInstitution(institutionModel);
        newCourse.setDuration(data.duration());
        newCourse.setCategory(data.category());
        newCourse.setFrequency(data.frequency());

        CourseModel savedCourse = courseService.create(newCourse);
        CourseResponseDTO dto = new CourseResponseDTO(
                savedCourse.getId(),
                savedCourse.getName(),
                savedCourse.getInstitution().getName(),
                savedCourse.getDuration(),
                savedCourse.getCategory(),
                savedCourse.getFrequency(),
                savedCourse.isActive()
        );
        return ResponseEntity.status(201).body(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar curso", description = "Atualiza os dados de um curso existente")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody UpdateCourseDTO data) {

        Optional<InstitutionModel> institutionOptional = institutionRepository.findByNameIgnoreCase(data.institution());

        if (institutionOptional.isEmpty()) {
            return ResponseEntity.status(404).body("Instituição não encontrada com o nome: " + data.institution());
        }

        CourseModel newData = new CourseModel();
        newData.setName(data.name());
        newData.setInstitution(institutionOptional.get());
        newData.setDuration(data.duration());
        newData.setCategory(data.category());
        newData.setFrequency(data.frequency());
        newData.setActive(data.isActive());

        try {
            CourseModel updatedCourse = courseService.save(id, newData);
            CourseResponseDTO dto = new CourseResponseDTO(
                    updatedCourse.getId(),
                    updatedCourse.getName(),
                    updatedCourse.getInstitution().getName(),
                    updatedCourse.getDuration(),
                    updatedCourse.getCategory(),
                    updatedCourse.getFrequency(),
                    updatedCourse.isActive()
            );
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar curso", description = "Remove um curso pelo ID")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        try {
            courseService.findById(id);
            courseService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
