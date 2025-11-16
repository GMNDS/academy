package com.gmnds.academy.repositories;

import com.gmnds.academy.models.StudentGradeModel;
import com.gmnds.academy.models.StudentModel;
import com.gmnds.academy.models.SubjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StudentGradeRepository extends JpaRepository<StudentGradeModel, Long> {
    boolean existsByGradeId(Long gradeId);
    boolean existsBySubjectId(Long subjectId);
    boolean existsByStudentId(Long studentId);
}
