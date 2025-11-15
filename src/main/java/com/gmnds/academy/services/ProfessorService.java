package com.gmnds.academy.services;

import com.gmnds.academy.models.ProfessorModel;
import com.gmnds.academy.repositories.ProfessorRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;


    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @CacheEvict(value = {"professors"}, allEntries = true)
    public ProfessorModel create(ProfessorModel professor) {
        return professorRepository.save(professor);
    }



    @Cacheable(value = "professors")
    public List<ProfessorModel> findAll() {
        return professorRepository.findAll();

    }


    @Cacheable(value = "professor", key = "#id")
    public ProfessorModel findById(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
    }


    @CachePut(value = "professor", key = "#id")
    @CacheEvict(value = {"professors"}, allEntries = true)
    public ProfessorModel save(Long id, ProfessorModel newData) {
        ProfessorModel professor = findById(id);

        professor.setName(newData.getName());

        return professorRepository.save(professor);
    }


    @CacheEvict(value= {"professors", "professor"}, allEntries = true)
    public void delete(Long id){
        professorRepository.deleteById(id);
    }
}
