package com.gmnds.academy.repositories;

import com.gmnds.academy.models.ExamModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<ExamModel, Long> {
}
