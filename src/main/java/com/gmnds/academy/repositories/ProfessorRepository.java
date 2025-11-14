package com.gmnds.academy.repositories;


import com.gmnds.academy.models.InstitutionModel;
import com.gmnds.academy.models.ProfessorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorModel, Long> {
    Optional<ProfessorModel> findByNameIgnoreCase(String name);
}
