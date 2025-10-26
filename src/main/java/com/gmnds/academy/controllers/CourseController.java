package com.gmnds.academy.controllers;

import com.gmnds.academy.models.CourseModel;
import com.gmnds.academy.repositories.CourseRepository;
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
    public CourseModel createCourse(@RequestBody CourseModel course) {
        return courseRepository.save(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseModel> updateCourse(@PathVariable Long id, @RequestBody CourseModel course) {
        Optional<CourseModel> existingCourse = courseRepository.findById(id);

        if (existingCourse.isPresent()) {
            CourseModel courseToUpdate = existingCourse.get();
            courseToUpdate.setName(course.getName());
            courseToUpdate.setInstitution(course.getInstitution());
            courseToUpdate.setDuration(course.getDuration());
            courseToUpdate.setCategory(course.getCategory());
            courseToUpdate.setFrequency(course.getFrequency());
            courseToUpdate.setActive(course.isActive());

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

        return ResponseEntity.notFound().build();
    }
}
