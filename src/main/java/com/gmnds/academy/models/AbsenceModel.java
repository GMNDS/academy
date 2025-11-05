package com.gmnds.academy.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "absences")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AbsenceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private StudentModel student;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    @JsonBackReference
    private SubjectModel subject;

    @Column(name = "total_classes")
    private int totalClasses;

    @Column(name = "attendances")
    private int attendances;

    private Double percentage;


    @PrePersist
    @PreUpdate
    private void calculatePercentage() {
        if (totalClasses > 0) {
            this.percentage = (attendances * 100.0) / totalClasses;
        }
    }


}
