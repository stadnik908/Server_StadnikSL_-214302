package com.certification.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Test implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;
    private boolean status = false;

    @ManyToOne
    private Department department;
    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();
    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    private List<Passing> passings = new ArrayList<>();

    public Test(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    public int getMinScore() {
        return questions.stream().reduce(0, (i, question) -> i + question.getMinScore(), Integer::sum);
    }

    public int getMaxScore() {
        return questions.stream().reduce(0, (i, question) -> i + question.getMaxScore(), Integer::sum);
    }

    public List<Question> getQuestions() {
        questions.sort(Comparator.comparing(Question::getId));
        return questions;
    }
}