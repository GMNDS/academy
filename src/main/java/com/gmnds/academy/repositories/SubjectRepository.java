package com.gmnds.academy.repositories;


import com.gmnds.academy.models.SubjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SubjectRepository extends JpaRepository<SubjectModel, Long> {
    Optional<SubjectModel> findByName(String name);
    boolean existsByCourseId(Long courseId);
    boolean existsByProfessorId(Long professorId);
}
