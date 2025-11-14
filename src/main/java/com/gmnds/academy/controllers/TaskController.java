package com.gmnds.academy.controllers;

import com.gmnds.academy.dto.AddTaskDTO;
import com.gmnds.academy.dto.UpdateTaskDTO;
import com.gmnds.academy.models.ExamModel;
import com.gmnds.academy.models.TaskModel;
import com.gmnds.academy.repositories.TaskRepository;
import com.gmnds.academy.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks" )
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    //@GetMapping
    //public List<TaskModel> getAllTasks() {
    //    return null;
    //}

    @GetMapping("/{id}")
    public ResponseEntity<TaskModel> getTaskById(@PathVariable Long id) {
        // Busca por ID
        return TaskRepository.findById(id)
                .map(task -> ResponseEntity.ok().body(task))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody AddTaskDTO data) {

        TaskModel newTask = new TaskModel();
        newTask.setStudent(data.student());
        newTask.setSubject(data.subject());
        newTask.setTitle(data.title());
        newTask.setDescription(data.description());
        newTask.setDueDate(data.dueDate());
        newTask.setCompletedAt(data.completedAt());

        TaskModel savedTask = taskRepository.save(newTask);
        return ResponseEntity.status(201).body(savedTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody UpdateTaskDTO data) {

        Optional<TaskModel> taskOptional = taskRepository.findById(id);

        if (taskOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        TaskModel existingTask = taskOptional.get();
        // Atualiza os campos no DTO
        existingTask.setStudent(data.student());
        existingTask.setSubject(data.subject());
        existingTask.setTitle(data.title());
        existingTask.setDescription(data.description());
        existingTask.setDueDate(data.dueDate());
        existingTask.setCompletedAt(data.completedAt());

        try {
            ExamModel updatedExam = taskService.update(id, existingTask);
            return ResponseEntity.ok(updatedExam);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        try {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    } catch (RuntimeException e) {
        return ResponseEntity.notFound().build();
    }
    }
}
