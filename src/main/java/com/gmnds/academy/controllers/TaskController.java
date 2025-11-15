package com.gmnds.academy.controllers;

import com.gmnds.academy.dto.AddTaskDTO;
import com.gmnds.academy.dto.TaskResponseDTO;
import com.gmnds.academy.dto.UpdateTaskDTO;
import com.gmnds.academy.models.TaskModel;
import com.gmnds.academy.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks" )
@Tag(name = "Tarefas", description = "Gerenciamento de tarefas e atividades")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    @Operation(summary = "Listar todas as tarefas", description = "Retorna a lista completa de tarefas cadastradas")
    public List<TaskResponseDTO> getAllTasks() {
        List<TaskModel> tasks = taskService.findAll();
        return tasks.stream()
                .map(task -> new TaskResponseDTO(
                        task.getId(),
                        task.getStudent() != null ? task.getStudent().getId() : null,
                        task.getStudent() != null ? task.getStudent().getName() : null,
                        task.getSubject() != null ? task.getSubject().getId() : null,
                        task.getSubject() != null ? task.getSubject().getName() : null,
                        task.getTitle(),
                        task.getDescription(),
                        task.getDueDate(),
                        task.getCompleted(),
                        task.getCompletedAt()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar tarefa por ID", description = "Retorna uma tarefa específica pelo ID")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id) {
        try {
            TaskModel task = taskService.findById(id);
            TaskResponseDTO dto = new TaskResponseDTO(
                    task.getId(),
                    task.getStudent() != null ? task.getStudent().getId() : null,
                    task.getStudent() != null ? task.getStudent().getName() : null,
                    task.getSubject() != null ? task.getSubject().getId() : null,
                    task.getSubject() != null ? task.getSubject().getName() : null,
                    task.getTitle(),
                    task.getDescription(),
                    task.getDueDate(),
                    task.getCompleted(),
                    task.getCompletedAt()
            );
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Criar tarefa", description = "Cadastra uma nova tarefa para um estudante em uma disciplina")
    public ResponseEntity<TaskModel> createTask(@RequestBody AddTaskDTO data) {
        TaskModel newTask = new TaskModel();
        newTask.setStudent(data.student());
        newTask.setSubject(data.subject());
        newTask.setTitle(data.title());
        newTask.setDescription(data.description());
        newTask.setDueDate(data.dueDate());
        newTask.setCompletedAt(data.completedAt());

        try {
            TaskModel savedTask = taskService.create(newTask);
            return ResponseEntity.status(201).body(savedTask);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar tarefa", description = "Atualiza os dados de uma tarefa existente")
    public ResponseEntity<TaskModel> updateTask(@PathVariable Long id, @RequestBody UpdateTaskDTO data) {
        TaskModel newData = new TaskModel();
        newData.setStudent(data.student());
        newData.setSubject(data.subject());
        newData.setTitle(data.title());
        newData.setDescription(data.description());
        newData.setDueDate(data.dueDate());
        newData.setCompletedAt(data.completedAt());

        try {
            TaskModel updatedTask = taskService.save(id, newData);
            return ResponseEntity.ok(updatedTask);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar tarefa", description = "Remove uma tarefa pelo ID")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        try {
            taskService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
