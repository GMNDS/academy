package com.gmnds.academy.services;


import com.gmnds.academy.models.AbsenceModel;
import com.gmnds.academy.repositories.AbsenceRepository;
import com.gmnds.academy.repositories.StudentRepository;
import com.gmnds.academy.repositories.SubjectRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbsenceService {
    private final AbsenceRepository absenceRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;


    public AbsenceService(AbsenceRepository absenceRepository, StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.absenceRepository = absenceRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    @CacheEvict(value = {"absences"}, allEntries = true)
    public AbsenceModel create(AbsenceModel absence) {
        validateStudent(absence.getStudent().getId());
        validateSubject(absence.getSubject().getId());
        return absenceRepository.save(absence);
    }



    @Cacheable(value = "absences")
    public List<AbsenceModel> findAll() {
        return absenceRepository.findAll();

    }


    @Cacheable(value = "absence", key = "#id")
    public AbsenceModel findById(Long id) {
        return absenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Falta não encontrada"));
    }


    @CachePut(value = "absence", key = "#id")
    @CacheEvict(value = {"absences"}, allEntries = true)
    public AbsenceModel save(Long id, AbsenceModel newData) {
        AbsenceModel absence = findById(id);

        absence.setTotalClasses(newData.getTotalClasses());
        absence.setAttendances(newData.getAttendances());

        if (newData.getStudent() != null) {
            validateStudent(newData.getStudent().getId());
            absence.setStudent(newData.getStudent());
        }

        if (newData.getSubject() != null) {
            validateSubject(newData.getSubject().getId());
            absence.setSubject(newData.getSubject());
        }

        return absenceRepository.save(absence);
    }


    @CacheEvict(value= {"absences", "absence"}, allEntries = true)
    public void delete(Long id){
        absenceRepository.deleteById(id);
    }

    private void validateStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new RuntimeException("Estudante não encontrado");
        }
    }

    private void validateSubject(Long subjectId) {
        boolean exists = subjectRepository.existsById(subjectId);
        if (!exists) {
            throw new RuntimeException("Matéria não encontrada");
        }
    }
}
