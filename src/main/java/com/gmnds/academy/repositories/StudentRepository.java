package com.gmnds.academy.repositories;

import com.gmnds.academy.models.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Long> {

    UserDetails findByEmail(String email);
    UserDetails findByRa(String ra);

    }
