package com.gmnds.academy.services;


import com.gmnds.academy.models.TaskModel;
import com.gmnds.academy.repositories.TaskRepository;
import com.gmnds.academy.repositories.StudentRepository;
import com.gmnds.academy.repositories.SubjectRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;


    public TaskService(TaskRepository taskRepository, StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.taskRepository = taskRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    @CacheEvict(value = {"tasks"}, allEntries = true)
    public TaskModel create(TaskModel task) {
        validateStudent(task.getStudent().getId());
        validateSubject(task.getSubject().getId());
        return taskRepository.save(task);
    }



    @Cacheable(value = "tasks")
    public List<TaskModel> findAll() {
        return taskRepository.findAll();

    }


    @Cacheable(value = "task", key = "#id")
    public TaskModel findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }


    @CachePut(value = "task", key = "#id")
    @CacheEvict(value = {"tasks"}, allEntries = true)
    public TaskModel save(Long id, TaskModel newData) {
        TaskModel task = findById(id);

        task.setTitle(newData.getTitle());
        task.setDescription(newData.getDescription());
        task.setDueDate(newData.getDueDate());
        task.setCompleted(newData.getCompleted());
        task.setCompletedAt(newData.getCompletedAt());

        if (newData.getStudent() != null) {
            validateStudent(newData.getStudent().getId());
            task.setStudent(newData.getStudent());
        }

        if (newData.getSubject() != null) {
            validateSubject(newData.getSubject().getId());
            task.setSubject(newData.getSubject());
        }

        return taskRepository.save(task);
    }


    @CacheEvict(value= {"tasks", "task"}, allEntries = true)
    public void delete(Long id){
        taskRepository.deleteById(id);
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
