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
public class Question implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;

    @ManyToOne
    private Test test;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    public Question(String name, Test test) {
        this.name = name;
        this.test = test;
    }

    public int getMinScore() {
        if (answers.isEmpty()) return 0;
        int res = answers.get(0).getScore();
        for (Answer i : answers) {
            res = Math.min(res, i.getScore());
        }
        return res;
    }

    public int getMaxScore() {
        if (answers.isEmpty()) return 0;
        int res = answers.get(0).getScore();
        for (Answer i : answers) {
            res = Math.max(res, i.getScore());
        }
        return res;
    }

    public List<Answer> getAnswers() {
        answers.sort(Comparator.comparing(Answer::getId));
        return answers;
    }
}