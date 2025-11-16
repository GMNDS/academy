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

    /**
     * Returns a lightweight DTO list with institution and course names filled when available.
     */
    public List<com.gmnds.academy.dto.StudentResponseDTO> findAllDTO() {
        var students = studentRepository.findAllWithInstitutionAndCourse();
        return students.stream().map(this::toDto).toList();
    }

    public com.gmnds.academy.dto.StudentResponseDTO findByIdDTO(Long id) {
        var optional = studentRepository.findByIdWithInstitutionAndCourse(id);
        if (optional.isPresent()) {
            return toDto(optional.get());
        }

        // fallback: check the cached list
        var all = findAllDTO();
        return all.stream().filter(dto -> dto.id().equals(id)).findFirst()
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado"));
    }

    private com.gmnds.academy.dto.StudentResponseDTO toDto(StudentModel s) {
        return new com.gmnds.academy.dto.StudentResponseDTO(
                s.getId(),
                s.getRa(),
                s.getName(),
                s.getPhoto(),
                s.getInstitution() != null ? s.getInstitution().getName() : null,
                s.getCourse() != null ? s.getCourse().getName() : null,
                s.getSemester(),
                s.getAverage_grade(),
                s.getEmail(),
                s.getRole(),
                s.isActive()
        );
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
