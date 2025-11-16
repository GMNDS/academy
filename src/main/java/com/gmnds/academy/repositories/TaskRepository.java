package com.gmnds.academy.repositories;


import com.gmnds.academy.models.SubjectModel;
import com.gmnds.academy.models.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Long> {
    boolean existsBySubjectId(Long subjectId);
    boolean existsByStudentId(Long studentId);
}
