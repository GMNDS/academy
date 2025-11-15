package com.gmnds.academy.services;

import com.gmnds.academy.models.StudentModel;
import com.gmnds.academy.repositories.StudentRepository;
import com.gmnds.academy.repositories.InstitutionRepository;
import com.gmnds.academy.repositories.CourseRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final InstitutionRepository institutionRepository;
    private final CourseRepository courseRepository;


    public StudentService(StudentRepository studentRepository, InstitutionRepository institutionRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.institutionRepository = institutionRepository;
        this.courseRepository = courseRepository;
    }

    @CacheEvict(value = {"students"}, allEntries = true)
    public StudentModel create(StudentModel student) {
        if (student.getInstitution() != null) {
            validateInstitution(student.getInstitution().getId());
        }
        if (student.getCourse() != null) {
            validateCourse(student.getCourse().getId());
        }
        return studentRepository.save(student);
    }



    @Cacheable(value = "students")
    public List<StudentModel> findAll() {
        return studentRepository.findAll();

    }


    @Cacheable(value = "student", key = "#id")
    public StudentModel findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado"));
    }


    @CachePut(value = "student", key = "#id")
    @CacheEvict(value = {"students"}, allEntries = true)
    public StudentModel save(Long id, StudentModel newData) {
        StudentModel student = findById(id);

        student.setRa(newData.getRa());
        student.setName(newData.getName());
        student.setPhoto(newData.getPhoto());
        student.setSemester(newData.getSemester());
        student.setAverage_grade(newData.getAverage_grade());
        student.setEmail(newData.getEmail());
        student.setActive(newData.isActive());

        if (newData.getInstitution() != null) {
            validateInstitution(newData.getInstitution().getId());
            student.setInstitution(newData.getInstitution());
        }

        if (newData.getCourse() != null) {
            validateCourse(newData.getCourse().getId());
            student.setCourse(newData.getCourse());
        }

        return studentRepository.save(student);
    }


    @CacheEvict(value= {"students", "student"}, allEntries = true)
    public void delete(Long id){
        studentRepository.deleteById(id);
    }

    private void validateInstitution(Long institutionId) {
        boolean exists = institutionRepository.existsById(institutionId);
        if (!exists) {
            throw new RuntimeException("Instituição não encontrada");
        }
    }

    private void validateCourse(Long courseId) {
        boolean exists = courseRepository.existsById(courseId);
        if (!exists) {
            throw new RuntimeException("Curso não encontrado");
        }
    }
}
