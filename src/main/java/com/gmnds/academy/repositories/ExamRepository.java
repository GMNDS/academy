package com.gmnds.academy.repositories;

import com.gmnds.academy.models.ExamModel;
import com.gmnds.academy.models.InstitutionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<ExamModel, Long> {
    Optional<ExamModel> findByDataIgnoreCase(LocalDate exam_date);
}
