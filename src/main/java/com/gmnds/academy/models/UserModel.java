package com.gmnds.academy.models;

import com.gmnds.academy.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String ra;
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
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private UserRole role;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
    @Column(nullable = false, columnDefinition = "boolean default true")
    @Builder.Default
    private boolean active = true;


    public UserModel(String ra, String email, String password, UserRole role) {
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
