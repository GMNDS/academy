package com.gmnds.academy.repositories;

import com.gmnds.academy.models.AbsenceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbsenceRepository extends JpaRepository<AbsenceModel, Long> {
    boolean existsBySubjectId(Long subjectId);
    boolean existsByStudentId(Long studentId);
}

