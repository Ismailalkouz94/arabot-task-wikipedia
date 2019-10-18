package com.example.wikipedia.service;


import com.example.wikipedia.model.Articles;

import java.util.List;
import java.util.Map;

public interface ArticlesService {

    void saveAll(List<Articles> articlesList);

    List<Articles> findArticlesByTitleOrSnippet(String text);

    Map<String, Object> getStatistics();

}
