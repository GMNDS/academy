package com.gmnds.academy.controllers;

import com.gmnds.academy.dto.AddCourseDTO;
import com.gmnds.academy.dto.UpdateCourseDTO;
import com.gmnds.academy.models.CourseModel;
import com.gmnds.academy.models.InstitutionModel;
import com.gmnds.academy.repositories.InstitutionRepository;
import com.gmnds.academy.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private InstitutionRepository institutionRepository;

    @GetMapping
    public List<CourseModel> getAllCourses() {
        return courseService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseModel> getCourseById(@PathVariable Long id) {
        try {
            CourseModel course = courseService.findById(id);
            return ResponseEntity.ok(course);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
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
        return ResponseEntity.status(201).body(savedCourse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseModel> updateCourse(@PathVariable Long id, @RequestBody UpdateCourseDTO data) {

        Optional<InstitutionModel> institutionOptional = institutionRepository.findByNameIgnoreCase(data.institution());

        CourseModel newData = new CourseModel();
        newData.setName(data.name());
        newData.setInstitution(institutionOptional.get());
        newData.setDuration(data.duration());
        newData.setCategory(data.category());
        newData.setFrequency(data.frequency());
        newData.setActive(data.isActive());

        try {
            CourseModel updatedCourse = courseService.update(id, newData);
            return ResponseEntity.ok(updatedCourse);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
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
