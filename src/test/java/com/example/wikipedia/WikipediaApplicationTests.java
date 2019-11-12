package com.example.wikipedia;

import com.example.wikipedia.model.Articles;
import com.example.wikipedia.service.ArticlesService;
import com.example.wikipedia.util.LoadPropertiesFile;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
class WikipediaApplicationTests {
    private static final Logger logger = LogManager.getLogger(WikipediaApplicationTests.class);

    public static final String WIKIPEDIA_URL = "https://en.wikipedia.org/w/api.php?";
    public static final String LOCAL_URL = "http://localhost:";
    public static final String MOUNT_POINT="/wikipedia/";
    LoadPropertiesFile loadPropertiesFile = new LoadPropertiesFile();
    String FUL_URL = LOCAL_URL + loadPropertiesFile.getConfig("server.port") + MOUNT_POINT + "articles/";

    @Autowired
    ArticlesService articlesService;

    @Test
    @Order(1)
    void getWikipediaApiAndStore() {
        logger.info("------------ Enter getWikipediaApiAndStore --------------------");
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<String>(headers);
            String result = restTemplate.exchange(WIKIPEDIA_URL + "action=query&list=search&srsearch=Amman,Jordan&format=json&srlimit=10", HttpMethod.GET, entity, String.class).getBody();
            JSONObject jsonObject = new JSONObject(result);
            logger.info(">>>>>>>>> Result form Wikipedia API >>>>>>>>>>> \n" + jsonObject);
            JSONObject queryObject = (JSONObject) jsonObject.get("query");
            Type listType = new TypeToken<List<Articles>>() {
            }.getType();
            List<Articles> articlesList = new Gson().fromJson(String.valueOf(queryObject.get("search")), listType);
            List<Articles> storedArticlesList = articlesService.saveAll(articlesList);

            assertEquals(articlesList.size(), storedArticlesList.size());
            logger.info("----------- Exit getWikipediaApiAndStore --------------------");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    void testSearchOnTitleOrBody() {
        logger.info("------------ Enter testSearchOnTitleOrBody --------------------");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<List<Articles>> entity = new HttpEntity<>(headers);
        String searchTesxt = "Abdullah";
        ResponseEntity<List<Articles>> response = restTemplate.exchange(FUL_URL + searchTesxt, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Articles>>() {
        });
        List<Articles> articlesList = response.getBody();
        logger.info(">>>>>>>>> Result form Database >>>>>>>>>>> ");
        articlesList.forEach(x -> logger.info(x));

        logger.info("----------- Exit testSearchOnTitleOrBody --------------------");

    }

    @Test
    @Order(3)
    void testStatisticsApi() {
        logger.info("------------ Enter testStatisticsApi --------------------");

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<String>(headers);
            String result = restTemplate.exchange(FUL_URL, HttpMethod.GET, entity, String.class).getBody();
            JSONObject jsonObject = new JSONObject(result);

            assertNotNull(jsonObject);
            logger.info(">>>>>>>>> Result form API >>>>>>>>>>> " + jsonObject);
            logger.info(">>>>>>>>> largest >>>>>>>>>>> " + jsonObject.get("largest"));
            logger.info(">>>>>>>>> Smallest >>>>>>>>>>> " + jsonObject.get("smallest"));
            logger.info(">>>>>>>>> Mediam >>>>>>>>>>> " + jsonObject.get("mediam"));
            logger.info(">>>>>>>>> allTotal >>>>>>>>>>> " + jsonObject.get("allTotal"));
            logger.info("----------- Exit testStatisticsApi --------------------");
        } catch (Exception e) {
            logger.error("----------- ERROR in  testStatisticsApi --------------------");
            logger.error(e.getMessage());
        }
    }

}
