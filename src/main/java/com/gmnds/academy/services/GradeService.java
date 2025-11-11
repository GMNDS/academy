package com.gmnds.academy.services;


import com.gmnds.academy.models.GradeModel;
import com.gmnds.academy.repositories.GradeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeRepository graderepository;

    public GradeModel create(GradeModel grade) {
        return graderepository.save(grade);
    }

    public List<GradeModel> findAll() {
        return graderepository.findAll();
    }

    public GradeModel findById(Long id) {
        return graderepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Grade não encontrada"));
    }


}
