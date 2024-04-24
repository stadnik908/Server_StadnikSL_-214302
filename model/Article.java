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
public class Article implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;
    private String author;
    private String theme;
    private String photo;
    private String file;
    @Column(length = 5000)
    private String description;

    public Article(String name, String author, String theme, String photo, String file, String description) {
        this.name = name;
        this.author = author;
        this.theme = theme;
        this.photo = photo;
        this.file = file;
        this.description = description;
    }

    public void set(String name, String author, String theme, String description) {
        this.name = name;
        this.author = author;
        this.theme = theme;
        this.description = description;
    }
}