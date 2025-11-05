package com.gmnds.academy.repositories;


import com.gmnds.academy.models.InstitutionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface InstitutionRepository extends JpaRepository<InstitutionModel, Long> {
    Optional<InstitutionModel> findByNameIgnoreCase(String name);
}
