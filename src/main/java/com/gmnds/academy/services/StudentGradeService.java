package com.gmnds.academy.services;


import com.gmnds.academy.models.StudentGradeModel;
import com.gmnds.academy.repositories.StudentGradeRepository;
import com.gmnds.academy.repositories.StudentRepository;
import com.gmnds.academy.repositories.GradeRepository;
import com.gmnds.academy.repositories.SubjectRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentGradeService {
    private final StudentGradeRepository studentGradeRepository;
    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;
    private final SubjectRepository subjectRepository;


    public StudentGradeService(StudentGradeRepository studentGradeRepository, StudentRepository studentRepository, GradeRepository gradeRepository, SubjectRepository subjectRepository) {
        this.studentGradeRepository = studentGradeRepository;
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
        this.subjectRepository = subjectRepository;
    }

    @CacheEvict(value = {"studentGrades"}, allEntries = true)
    public StudentGradeModel create(StudentGradeModel studentGrade) {
        validateStudent(studentGrade.getStudent().getId());
        validateGrade(studentGrade.getGrade().getId());
        validateSubject(studentGrade.getSubject().getId());
        return studentGradeRepository.save(studentGrade);
    }



    @Cacheable(value = "studentGrades")
    public List<StudentGradeModel> findAll() {
        return studentGradeRepository.findAll();

    }


    @Cacheable(value = "studentGrade", key = "#id")
    public StudentGradeModel findById(Long id) {
        return studentGradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota do estudante não encontrada"));
    }


    @CachePut(value = "studentGrade", key = "#id")
    @CacheEvict(value = {"studentGrades"}, allEntries = true)
    public StudentGradeModel save(Long id, StudentGradeModel newData) {
        StudentGradeModel studentGrade = findById(id);

        studentGrade.setScore(newData.getScore());

        if (newData.getStudent() != null) {
            validateStudent(newData.getStudent().getId());
            studentGrade.setStudent(newData.getStudent());
        }

        if (newData.getGrade() != null) {
            validateGrade(newData.getGrade().getId());
            studentGrade.setGrade(newData.getGrade());
        }

        if (newData.getSubject() != null) {
            validateSubject(newData.getSubject().getId());
            studentGrade.setSubject(newData.getSubject());
        }

        return studentGradeRepository.save(studentGrade);
    }


    @CacheEvict(value= {"studentGrades", "studentGrade"}, allEntries = true)
    public void delete(Long id){
        studentGradeRepository.deleteById(id);
    }

    private void validateStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new RuntimeException("Estudante não encontrado");
        }
    }

    private void validateGrade(Long gradeId) {
        boolean exists = gradeRepository.existsById(gradeId);
        if (!exists) {
            throw new RuntimeException("Peso de avaliação não encontrado");
        }
    }

    private void validateSubject(Long subjectId) {
        boolean exists = subjectRepository.existsById(subjectId);
        if (!exists) {
            throw new RuntimeException("Matéria não encontrada");
        }
    }
}
