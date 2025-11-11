package com.gmnds.academy.repositories;


import com.gmnds.academy.models.GradeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<GradeModel, Long> {

}
