package com.gmnds.academy.repositories;

import com.gmnds.academy.models.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Long> {

    UserDetails findByEmail(String email);
    UserDetails findByRa(String ra);

    @Query("select s from StudentModel s left join fetch s.institution left join fetch s.course where s.id = :id")
    Optional<StudentModel> findByIdWithInstitutionAndCourse(Long id);

    @Query("select s from StudentModel s left join fetch s.institution left join fetch s.course")
    List<StudentModel> findAllWithInstitutionAndCourse();

    }
