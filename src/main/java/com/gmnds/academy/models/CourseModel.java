package com.gmnds.academy.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String course_name;
    private String category;
    private Integer duration;
    private Integer frequency;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private InstitutionModel institution;

}
