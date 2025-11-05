package com.gmnds.academy.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gmnds.academy.enums.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do estudante", example = "1")
    private Long id;

    @Column(unique = true, nullable = false)
    @Schema(description = "Registro Acadêmico do estudante", example = "20210012345")
    private String ra;

    @Schema(description = "Nome completo do estudante", example = "Orion Steele")
    private String name;

    @Schema(description = "URL da foto do estudante", example = "https://example.com/photo.jpg")
    private String photo;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    @Schema(description = "Instituição de ensino do estudante")
    private InstitutionModel institution;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @Schema(description = "Curso do estudante")
    private CourseModel course;

    @Schema(description = "Semestre atual do estudante", example = "3")
    private Integer semester;

    @Schema(description = "Média de notas do estudante", example = "8.5")
    private Double average_grade;

    // Autenticação
    @Column(unique = true, nullable = false)
    @Schema(description = "Email do estudante", example = "orion@gmnds.com")
    private String email;

    @Schema(description = "Senha do estudante", example = "senhaSegura123!")
    private String password;

    @Schema(description = "Papel do estudante no sistema", example = "STUDENT", allowableValues = {"ADMIN", "STUDENT"})
    private UserRole role;

    // Controle
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false, columnDefinition = "boolean default true")
    @Builder.Default
    private boolean active = true;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<StudentGradeModel> studentGrades;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<AbsenceModel> absences;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<TaskModel> tasks;

    public StudentModel(String ra, String email, String password, UserRole role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.ra = ra;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority adminAuthority = () -> "ROLE_ADMIN";
        GrantedAuthority userAuthority = () -> "ROLE_USER";
        if(this.role == UserRole.ADMIN) {
            return List.of(adminAuthority, userAuthority);
        }
         return List.of(userAuthority);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
