package com.gmnds.academy.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
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


    public UserModel() {}


    public UserModel(Long ra, String name, String photo, InstitutionModel institution, CourseModel course,
                     Integer semester, Double average_grade, Integer absences, String email) {
        this.ra = ra;
        this.name = name;
        this.photo = photo;
        this.institution = institution;
        this.course = course;
        this.semester = semester;
        this.average_grade = average_grade;
        this.absences = absences;
        this.email = email;
    }


    public Long getRa() {
        return ra;
    }

    public void setRa(Long ra) {
        this.ra = ra;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public InstitutionModel getInstitution() {
        return institution;
    }

    public void setInstitution(InstitutionModel institution) {
        this.institution = institution;
    }

    public CourseModel getCourse() {
        return course;
    }

    public void setCourse(CourseModel course) {
        this.course = course;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Double getAverage_grade() {
        return average_grade;
    }

    public void setAverage_grade(Double average_grade) {
        this.average_grade = average_grade;
    }

    public Integer getAbsences() {
        return absences;
    }

    public void setAbsences(Integer absences) {
        this.absences = absences;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
