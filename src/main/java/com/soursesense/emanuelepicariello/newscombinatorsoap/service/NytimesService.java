package com.soursesense.emanuelepicariello.newscombinatorsoap.service;

import com.soursesense.emanuelepicariello.newscombinatorsoap.news.News;
import com.soursesense.emanuelepicariello.newscombinatorsoap.news.NyTimes;
import com.soursesense.emanuelepicariello.newscombinatorsoap.news.NyTimesContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class NytimesService {
    private static final Logger logger = LoggerFactory.getLogger(HackerNewsService.class);

    @Value("${nyTimesNews}")
    private String nyTimesNews;


    public List<News> getAllArticleOfNyTimes() throws IOException {
        logger.info("creation of NyTimes list");
        return printNews();

    }

    public List<News> printNews() {
        List<News> allNyTimesArticles = new ArrayList<>();
        List<NyTimes> allArticles = allTheArticlesOFNyTimes().getResults();
        allNyTimesArticles.addAll(allArticles);

        return allNyTimesArticles;
    }

    public NyTimesContainer allTheArticlesOFNyTimes() {
        RestTemplate restTemplate = new RestTemplate();
        NyTimesContainer allNyTimesArticlesContainers = restTemplate.getForObject(nyTimesNews, NyTimesContainer.class);

        return allNyTimesArticlesContainers;

    }


}
