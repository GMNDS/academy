package com.gmnds.academy.repositories;


import com.gmnds.academy.models.SubjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SubjectRepository extends JpaRepository<SubjectModel, Long> {
}
