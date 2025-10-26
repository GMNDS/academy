package com.gmnds.academy.repositories;


import com.gmnds.academy.models.InstitutionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionRepository extends JpaRepository<InstitutionModel, Long> {
}
