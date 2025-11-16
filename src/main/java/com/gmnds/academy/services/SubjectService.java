package com.gmnds.academy.services;

import com.gmnds.academy.models.SubjectModel;
import com.gmnds.academy.repositories.SubjectRepository;
import com.gmnds.academy.repositories.CourseRepository;
import com.gmnds.academy.repositories.ProfessorRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;


    public SubjectService(SubjectRepository subjectRepository, CourseRepository courseRepository, ProfessorRepository professorRepository) {
        this.subjectRepository = subjectRepository;
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
    }

    @Transactional
    @CacheEvict(value = {"subjects"}, allEntries = true)
    public SubjectModel create(SubjectModel subject) {
        // Validar se o curso existe e tem ID
        if (subject.getCourse() == null) {
            throw new RuntimeException("Curso não pode ser nulo");
        }
        if (subject.getCourse().getId() == null) {
            throw new RuntimeException("O ID do curso não pode ser nulo");
        }
        validateCourse(subject.getCourse().getId());
        // Professor já é criado no controller, não precisa validar aqui
        return subjectRepository.save(subject);
    }



    @Cacheable(value = "subjects")
    public List<SubjectModel> findAll() {
        return subjectRepository.findAll();

    }


    @Cacheable(value = "subject", key = "#id")
    public SubjectModel findById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matéria não encontrada"));
    }


    @CachePut(value = "subject", key = "#id")
    @CacheEvict(value = {"subjects"}, allEntries = true)
    public SubjectModel save(Long id, SubjectModel newData) {
        SubjectModel subject = findById(id);

        subject.setName(newData.getName());
        subject.setCode(newData.getCode());
        subject.setTotalClasses(newData.getTotalClasses());

        if (newData.getCourse() != null) {
            validateCourse(newData.getCourse().getId());
            subject.setCourse(newData.getCourse());
        }

        if (newData.getProfessor() != null) {
            validateProfessor(newData.getProfessor().getId());
            subject.setProfessor(newData.getProfessor());
        }

        return subjectRepository.save(subject);
    }


    @CacheEvict(value= {"subjects", "subject"}, allEntries = true)
    public void delete(Long id){
        subjectRepository.deleteById(id);
    }

    private void validateCourse(Long courseId) {
        boolean exists = courseRepository.existsById(courseId);
        if (!exists) {
            throw new RuntimeException("Curso não encontrado");
        }
    }

    private void validateProfessor(Long professorId) {
        boolean exists = professorRepository.existsById(professorId);
        if (!exists) {
            throw new RuntimeException("Professor não encontrado");
        }
    }
}
