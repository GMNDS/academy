package com.gmnds.academy.controllers;

import com.gmnds.academy.infra.security.TokenService;
import com.gmnds.academy.models.StudentModel;
import com.gmnds.academy.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository repStudent;
    @Autowired
    private TokenService tokenService;

    @GetMapping
    public List<StudentModel> getAllStudents() {
        return repStudent.findAll();
    }

    private boolean validateStudentAcess(Long id, String authorization) {

        String token = authorization.replace("Bearer ", "");
        Long authenticatedStudentId = tokenService.getUserId(token);

        return id.equals(authenticatedStudentId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentModel> getStudentById(@PathVariable  Long id, @RequestHeader("Authorization") String authorization) {
        if (!validateStudentAcess(id, authorization)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return repStudent.findById(id)
                .map(Student -> ResponseEntity.ok().body(Student))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentModel> updateStudent(@PathVariable Long id, @RequestBody StudentModel Student, @RequestHeader("Authorization") String authorization) {
        if (!validateStudentAcess(id, authorization)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Optional<StudentModel> us = repStudent.findById(id);

        if (us.isPresent()) {
            StudentModel existingStudent = us.get();
            existingStudent.setName(Student.getName());
            existingStudent.setEmail(Student.getEmail());
            existingStudent.setPassword(Student.getPassword());

            StudentModel updatedStudent = repStudent.save(existingStudent);
            return ResponseEntity.ok(updatedStudent);
        }
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteStudent(@RequestHeader("Authorization") String authorization) {
        String token = authorization.replace("Bearer ", "");
        Long authenticatedStudentId = tokenService.getUserId(token);

        Optional<StudentModel> us = repStudent.findById(authenticatedStudentId);

        if (us.isPresent()) {
            boolean active = us.get().isActive();
            if (active) {
                us.get().setActive(false);
                repStudent.save(us.get());
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.notFound().build();
    }

}
