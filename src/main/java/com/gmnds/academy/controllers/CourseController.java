package com.gmnds.academy.controllers;

import com.gmnds.academy.dto.AddCourseDTO;
import com.gmnds.academy.dto.UpdateCourseDTO;
import com.gmnds.academy.models.CourseModel;
import com.gmnds.academy.models.InstitutionModel;
import com.gmnds.academy.repositories.CourseRepository;
import com.gmnds.academy.repositories.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @GetMapping
    public List<CourseModel> getAllCourses() {
        return courseRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseModel> getCourseById(@PathVariable Long id) {
        return courseRepository.findById(id)
                .map(course -> ResponseEntity.ok().body(course))
                .orElse(ResponseEntity.notFound().build());
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

        CourseModel savedCourse = courseRepository.save(newCourse);
        return ResponseEntity.status(201).body(savedCourse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseModel> updateCourse(@PathVariable Long id, @RequestBody UpdateCourseDTO data) {

        Optional<CourseModel> existingCourse = courseRepository.findById(id);

        Optional<InstitutionModel> institutionOptional = institutionRepository.findByNameIgnoreCase(data.institution());

        if (existingCourse.isPresent()) {
            CourseModel courseToUpdate = existingCourse.get();
            courseToUpdate.setName(data.name());
            courseToUpdate.setInstitution(institutionOptional.get());
            courseToUpdate.setDuration(data.duration());
            courseToUpdate.setCategory(data.category());
            courseToUpdate.setFrequency(data.frequency());
            courseToUpdate.setActive(data.isActive());

            CourseModel updatedCourse = courseRepository.save(courseToUpdate);
            return ResponseEntity.ok(updatedCourse);
        }

        return ResponseEntity.notFound().build();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        courseRepository.deleteById(id);
        return ResponseEntity.notFound().build();
    }
}
