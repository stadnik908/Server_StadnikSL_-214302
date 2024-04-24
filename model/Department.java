package com.certification.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Department implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "department")
    private List<AppUser> users = new ArrayList<>();
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Test> tests = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }
}