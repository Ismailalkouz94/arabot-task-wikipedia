package com.example.wikipedia.service;

import com.example.wikipedia.dao.ArticlesDAO;
import com.example.wikipedia.model.Articles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticlesServiceImpl implements ArticlesService {
    private static final Logger logger = LogManager.getLogger(ArticlesServiceImpl.class);

    @Autowired
    ArticlesDAO articlesDAO;

    public List<Articles> saveAll(List<Articles> articlesList) {
        List<Articles> savedRow = new ArrayList<>();
        try {
             savedRow = articlesDAO.saveAll(articlesList);
            logger.info(">>>>>>>> Rows Stored in DB >>>>>>>> " + savedRow.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return savedRow;
    }

    @Override
    public List<Articles> findArticlesByTitleOrSnippet(String text) {
        try {
            return articlesDAO.getArticlesByTitleContainsOrSnippetContains(text, text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, Object> getStatistics() {
        Map<String, Object> statistics = new HashMap<>();
        int count;
        try {
            count = (int) (articlesDAO.countArticlesByIdIsNotNull() / 2);
            statistics.put("smallest", articlesDAO.findFirstByOrderByWordcountAsc());
            statistics.put("largest", articlesDAO.findFirstByOrderByWordcountDesc());
            statistics.put("mediam", articlesDAO.findByTop(PageRequest.of(count, count)));
            statistics.put("allTotal", articlesDAO.getTotalWordCount());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return statistics;
    }


}