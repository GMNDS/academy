package com.gmnds.academy.services;

import com.gmnds.academy.models.CourseModel;
import com.gmnds.academy.repositories.CourseRepository;
import com.gmnds.academy.repositories.InstitutionRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final InstitutionRepository institutionRepository;


    public CourseService(CourseRepository courseRepository, InstitutionRepository institutionRepository) {
        this.courseRepository = courseRepository;
        this.institutionRepository = institutionRepository;
    }

    @CacheEvict(value = {"courses"}, allEntries = true)
    public CourseModel create(CourseModel course) {
        validateInstitution(course.getInstitution().getId());
        return courseRepository.save(course);
    }



    @Cacheable(value = "courses")
    public List<CourseModel> findAll() {
        return courseRepository.findAll();

    }


    @Cacheable(value = "course", key = "#id")
    public CourseModel findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
    }


    @CachePut(value = "course", key = "#id")
    @CacheEvict(value = {"courses"}, allEntries = true)
    public CourseModel save(Long id, CourseModel newData) {
        CourseModel course = findById(id);

        course.setName(newData.getName());
        course.setDuration(newData.getDuration());
        course.setCategory(newData.getCategory());
        course.setFrequency(newData.getFrequency());
        course.setActive(newData.isActive());

        if (newData.getInstitution() != null) {
            validateInstitution(newData.getInstitution().getId());
            course.setInstitution(newData.getInstitution());
        }

        return courseRepository.save(course);
    }


    @CacheEvict(value= {"courses", "course"}, allEntries = true)
    public void delete(Long id){
        courseRepository.deleteById(id);
    }

    private void validateInstitution(Long institutionId) {
        boolean exists = institutionRepository.existsById(institutionId);
        if (!exists) {
            throw new RuntimeException("Instituição não encontrada");
        }

    }
}
