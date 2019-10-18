package com.example.wikipedia.controller;

import com.example.wikipedia.model.Articles;
import com.example.wikipedia.service.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ArticlesController {

    @Autowired
    ArticlesService articlesService;

    @RequestMapping(value = "/articles/{text}", method = RequestMethod.GET)
    public ResponseEntity<List<Articles>> findArticles(@PathVariable String text) {
        List<Articles> articlesList = null;
        try {
            articlesList = articlesService.findArticlesByTitleOrSnippet(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(articlesList, HttpStatus.OK);
    }

    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> articlesStatistics() {
        Map<String, Object> statistics = new HashMap<>();
        try {
            statistics = articlesService.getStatistics();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }

}
