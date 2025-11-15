package com.gmnds.academy.services;

import com.gmnds.academy.models.InstitutionModel;
import com.gmnds.academy.repositories.InstitutionRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutionService {
    private final InstitutionRepository institutionRepository;


    public InstitutionService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @CacheEvict(value = {"institutions"}, allEntries = true)
    public InstitutionModel create(InstitutionModel institution) {
        return institutionRepository.save(institution);
    }



    @Cacheable(value = "institutions")
    public List<InstitutionModel> findAll() {
        return institutionRepository.findAll();

    }


    @Cacheable(value = "institution", key = "#id")
    public InstitutionModel findById(Long id) {
        return institutionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instituição não encontrada"));
    }


    @CachePut(value = "institution", key = "#id")
    @CacheEvict(value = {"institutions"}, allEntries = true)
    public InstitutionModel save(Long id, InstitutionModel newData) {
        InstitutionModel institution = findById(id);

        institution.setName(newData.getName());
        institution.setAcronym(newData.getAcronym());
        institution.setType(newData.getType());
        institution.setActive(newData.isActive());

        return institutionRepository.save(institution);
    }


    @CacheEvict(value= {"institutions", "institution"}, allEntries = true)
    public void delete(Long id){
        institutionRepository.deleteById(id);
    }
}
