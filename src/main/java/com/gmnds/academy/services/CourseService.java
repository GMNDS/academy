package com.gmnds.academy.services;

import com.gmnds.academy.models.CourseModel;
import com.gmnds.academy.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<CourseModel> findAll() {
        return courseRepository.findAll();
    }

    @Cacheable(value = "course", key = "#id")
    public CourseModel findById(Long id) {
        Optional<CourseModel> courseOptional = courseRepository.findById(id);
        if (courseOptional.isEmpty()) {
            throw new RuntimeException("Course not found");
        }
        return courseOptional.get();
    }

    @CachePut(value = "course", key = "#result.id")
    public CourseModel create(CourseModel course) {
        return courseRepository.save(course);
    }

    @CachePut(value = "course", key = "#id")
    public CourseModel update(Long id, CourseModel newData) {
        CourseModel existingCourse = findById(id);
        existingCourse.setName(newData.getName());
        existingCourse.setInstitution(newData.getInstitution());
        existingCourse.setDuration(newData.getDuration());
        existingCourse.setCategory(newData.getCategory());
        existingCourse.setFrequency(newData.getFrequency());
        existingCourse.setActive(newData.isActive());
        return courseRepository.save(existingCourse);
    }

    @CacheEvict(value = "course", key = "#id")
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }
}
