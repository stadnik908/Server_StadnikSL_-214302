package com.certification.model;

import com.certification.controller.main.Main;
import com.certification.model.enums.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AppUser implements UserDetails, Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String username;
    private String password;
    private String photo = "def.jpg";
    private String fio;
    private boolean enabled = false;
    private boolean certification = false;
    private String certificationDate = "";
    private String certificationName = "";
    private int certificationScore = 0;
    private int certificationScoreMax = 0;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @OneToMany(mappedBy = "owner")
    private List<Passing> passings = new ArrayList<>();
    @ManyToOne
    private Department department;

    public AppUser(String username, String password, String fio) {
        this.username = username;
        this.password = passwordEncoder().encode(password);
        this.fio = fio;
    }

    public boolean checkDepartment(Long departmentId) {
        if (this.department != null) {
            return Objects.equals(this.department.getId(), departmentId);
        }
        return false;
    }

    public float getAvgPassingResult() {
        return Main.round((passings.stream().reduce(0f, (i, passing) -> i + passing.getResult(), Float::sum) / passings.size()));
    }

    public float getCertificationPercent() {
        if (certificationScoreMax == 0) return 0;
        return Main.round((float) certificationScore / certificationScoreMax * 100);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(getRole());
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}