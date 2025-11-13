package com.gmnds.academy.repositories;

import com.gmnds.academy.models.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseModel, Long> {
}
