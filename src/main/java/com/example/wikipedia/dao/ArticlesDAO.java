package com.example.wikipedia.dao;

import com.example.wikipedia.model.Articles;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ArticlesDAO extends JpaRepository<Articles, Long> {

    List<Articles> getArticlesByTitleContainsOrSnippetContains(String text, String text2);

    Long countArticlesByIdIsNotNull();

    @Query("SELECT SUM(a.wordcount) from Articles a")
    long getTotalWordCount();

    @Query("SELECT a FROM Articles a  ORDER BY a.wordcount DESC")
    List<Articles> findByTop(Pageable pageable);

    Articles findFirstByOrderByWordcountDesc();

    Articles findFirstByOrderByWordcountAsc();

}

