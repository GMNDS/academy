package com.gmnds.academy.models;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
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

    public CourseModel() {}

    public CourseModel(Long id, String course_name, String category, Integer duration, Integer frequency, InstitutionModel institution) {

        this.id = id;
        this.course_name = course_name;
        this.category = category;
        this.duration = duration;
        this.frequency = frequency;
        this.institution = institution;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getCourse_name() {
        return course_name;
    }
    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getDuration() {
        return duration;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getFrequency() {
        return frequency;
    }
    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public InstitutionModel getInstitution() {
        return institution;
    }
    public void setInstitution(InstitutionModel institution) {
        this.institution = institution;
    }
}
