package com.gmnds.academy.repositories;


import com.gmnds.academy.models.GradeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<GradeModel, Long> {
    Optional<GradeModel> findByName(String name);
}
