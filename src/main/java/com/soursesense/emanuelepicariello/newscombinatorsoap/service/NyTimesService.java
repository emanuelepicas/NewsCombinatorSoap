package com.soursesense.emanuelepicariello.newscombinatorsoap.service;

import com.soursesense.emanuelepicariello.newscombinatorsoap.mapper.NewsMapper;
import com.soursesense.emanuelepicariello.newscombinatorsoap.model.NyTimesArticleContainer;
import com.soursesense.emanuelepicariello.newscombinatorsoap.model.NyTimesArticleEntity;
import com.soursesense.emanuelepicariello.newscombinatorsoap.news.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class NyTimesService {
    private static final Logger logger = LoggerFactory.getLogger(HackerNewsService.class);

    @Value("${nyTimesNews}")
    private String nyTimesNews;


    public List<News> getAllArticleOfNyTimes() throws IOException, ExecutionException, InterruptedException {
        logger.info("creation of NyTimes list");


        return mapping();

    }

    public List<NyTimesArticleEntity> printNews() {
        List<NyTimesArticleEntity> allNyTimesArticles = new ArrayList<>();
        NyTimesArticleEntity[] allArticles = allTheArticlesOFNyTimes().getResults();
Collections.addAll(allNyTimesArticles,allArticles);
Collections.sort(allNyTimesArticles);
        return allNyTimesArticles;
    }

    public List<News> mapping() throws ExecutionException, InterruptedException {
        List<News> news;
        List<NyTimesArticleEntity> newsEntities=printNews();
        news=newsEntities.parallelStream().map(p->
                ( NewsMapper.INSTANCE.nyTimesArticleEntityToNews(p)))
                        .collect(Collectors.toList());
        return news;
    }

    public NyTimesArticleContainer allTheArticlesOFNyTimes() {
        RestTemplate restTemplate = new RestTemplate();
        NyTimesArticleContainer allNyTimesArticlesContainers = restTemplate.getForObject(nyTimesNews, NyTimesArticleContainer.class);

        return allNyTimesArticlesContainers;

    }

    public GetNewsResponse getNyTimesResponse() throws ExecutionException, InterruptedException, IOException {
        GetNewsResponse response=new GetNewsResponse();
        response.getNews().addAll(getAllArticleOfNyTimes());
        return response;

    }


}
