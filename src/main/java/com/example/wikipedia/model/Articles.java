package com.example.wikipedia.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ARTICLES")
public class Articles implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "WORD_COUNT")
    private Long wordcount;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "SNIPPET",columnDefinition="TEXT")
    private String snippet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWordcount() {
        return wordcount;
    }

    public void setWordcount(Long wordcount) {
        this.wordcount = wordcount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    @Override
    public String toString() {
        return "Articles{" +
                "id=" + id +
                ", wordcount=" + wordcount +
                ", title='" + title + '\'' +
                ", snippet='" + snippet + '\'' +
                '}';
    }
}
