package com.gmnds.academy.services;


import com.gmnds.academy.models.ExamModel;
import com.gmnds.academy.repositories.ExamRepository;
import com.gmnds.academy.repositories.SubjectRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {
    private final ExamRepository examRepository;
    private final SubjectRepository subjectRepository;


    public ExamService(ExamRepository examRepository, SubjectRepository subjectRepository) {
        this.examRepository = examRepository;
        this.subjectRepository = subjectRepository;
    }

    @CacheEvict(value = {"exams"}, allEntries = true)
    public ExamModel create(ExamModel exam) {
        validateSubject(exam.getSubject().getId());
        return examRepository.save(exam);
    }



    @Cacheable(value = "exams")
    public List<ExamModel> findAll() {
        return examRepository.findAll();

    }


    @Cacheable(value = "exam", key = "#id")
    public ExamModel findById(Long id) {
        return examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prova não encontrada"));
    }


    @CachePut(value = "exam", key = "#id")
    @CacheEvict(value = {"exams"}, allEntries = true)
    public ExamModel save(Long id, ExamModel newData) {
        ExamModel exam = findById(id);

        exam.setExamDate(newData.getExamDate());
        exam.setType(newData.getType());

        if (newData.getSubject() != null) {
            validateSubject(newData.getSubject().getId());
            exam.setSubject(newData.getSubject());
        }

        return examRepository.save(exam);
    }


    @CacheEvict(value= {"exams", "exam"}, allEntries = true)
    public void delete(Long id){
        examRepository.deleteById(id);
    }

    private void validateSubject(Long subjectId) {
        boolean exists = subjectRepository.existsById(subjectId);
        if (!exists) {
            throw new RuntimeException("Matéria não encontrada");
        }

    }
}