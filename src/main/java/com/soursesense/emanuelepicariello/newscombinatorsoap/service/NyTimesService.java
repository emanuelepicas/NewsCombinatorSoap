package com.soursesense.emanuelepicariello.newscombinatorsoap.service;

import com.soursesense.emanuelepicariello.newscombinatorsoap.mapper.NyTimesMapper;
import com.soursesense.emanuelepicariello.newscombinatorsoap.model.NewsEntity;
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
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@Service
public class NyTimesService {
    private static final Logger logger = LoggerFactory.getLogger(HackerNewsService.class);

    @Value("${nyTimesNews}")
    private String nyTimesNews;


    public List<NyTimes> getAllArticleOfNyTimes() throws IOException, ExecutionException, InterruptedException {
        logger.info("creation of NyTimes list");


        return mapping();

    }

    public List<NyTimesArticleEntity> printNews() {
        List<NyTimesArticleEntity> allNyTimesArticles = new ArrayList<>();
        NyTimesArticleEntity[] allArticles = allTheArticlesOFNyTimes().getResults();
Collections.addAll(allNyTimesArticles,allArticles);
        return allNyTimesArticles;
    }

    public List<NyTimes> mapping() throws ExecutionException, InterruptedException {
        List<NyTimes> news;
        List<NyTimesArticleEntity> newsEntities=printNews();
        news=newsEntities.parallelStream().map(p->
                ( NyTimesMapper.INSTANCE.nyTimesArticleEntityToNyTimes(p)))
                        .collect(Collectors.toList());
        return news;
    }

    public NyTimesArticleContainer allTheArticlesOFNyTimes() {
        RestTemplate restTemplate = new RestTemplate();
        NyTimesArticleContainer allNyTimesArticlesContainers = restTemplate.getForObject(nyTimesNews, NyTimesArticleContainer.class);

        return allNyTimesArticlesContainers;

    }

    public GetHackerNewsResponse getNyTimesResponse() throws ExecutionException, InterruptedException, IOException {
        GetHackerNewsResponse response=new GetHackerNewsResponse();
        response.getNews().addAll(getAllArticleOfNyTimes());
        return response;

    }


}
