package com.certification.model;

import com.certification.controller.main.Main;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Passing implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private int score = 0;
    private boolean status = false;

    @ManyToOne
    private Test test;
    @ManyToOne
    private AppUser owner;

    public Passing(Test test, AppUser owner) {
        this.test = test;
        this.owner = owner;
    }

    public float getResult() {
        return Main.round((float) score / test.getMaxScore() * 100);
    }
}