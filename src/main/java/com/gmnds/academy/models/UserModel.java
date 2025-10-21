package com.gmnds.academy.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ra;
    private String name;
    private String photo;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private InstitutionModel institution;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseModel course;

    private Integer semester;
    private Double average_grade;
    private Integer absences;
    private String email;
}
