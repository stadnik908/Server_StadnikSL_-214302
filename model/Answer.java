package com.certification.model;

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
public class Answer implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;
    private int score;

    @ManyToOne
    private Question question;

    public Answer(String name, int score, Question question) {
        this.name = name;
        this.score = score;
        this.question = question;
    }

    public void set(String name, int score) {
        this.name = name;
        this.score = score;
    }
}