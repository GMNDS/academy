package com.gmnds.academy.dto;

import com.gmnds.academy.models.StudentModel;
import com.gmnds.academy.models.SubjectModel;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AddTaskDTO(StudentModel student, SubjectModel subject, String title, String description, LocalDateTime dueDate, LocalDateTime completedAt) {
}


