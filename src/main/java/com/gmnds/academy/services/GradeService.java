package com.gmnds.academy.services;


import com.gmnds.academy.models.GradeModel;
import com.gmnds.academy.repositories.GradeRepository;
import com.gmnds.academy.repositories.InstitutionRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {
    private final GradeRepository gradeRepository;
    private final InstitutionRepository institutionRepository;


    public GradeService(GradeRepository gradeRepository, InstitutionRepository institutionRepository) {
        this.gradeRepository = gradeRepository;
        this.institutionRepository = institutionRepository;
    }

    @CacheEvict(value = {"grades"}, allEntries = true)
    public GradeModel create(GradeModel grade) {
        validateInstitution(grade.getInstitution().getId());
        return gradeRepository.save(grade);
    }



    @Cacheable(value = "grades")
    public List<GradeModel> findAll() {
        return gradeRepository.findAll();

    }


    @Cacheable(value = "grade", key = "#id")
    public GradeModel findById(Long id) {
        return gradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Peso de avaliação não encontrado"));
    }


    @CachePut(value = "grade", key = "#id")
    @CacheEvict(value = {"grades"}, allEntries = true)
    public GradeModel save(Long id, GradeModel newData) {
        GradeModel grade = findById(id);

        grade.setName(newData.getName());
        grade.setWeight(newData.getWeight());

        if (newData.getInstitution() != null) {
            validateInstitution(newData.getInstitution().getId());
            grade.setInstitution(newData.getInstitution());
        }

        return gradeRepository.save(grade);
    }


    @CacheEvict(value= {"grades", "grade"}, allEntries = true)
    public void delete(Long id){
        gradeRepository.deleteById(id);
    }

    private void validateInstitution(Long institutionId) {
        boolean exists = institutionRepository.existsById(institutionId);
        if (!exists) {
            throw new RuntimeException("Instituição não encontrada");
        }

    }
}
