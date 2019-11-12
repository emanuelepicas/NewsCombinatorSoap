package com.soursesense.emanuelepicariello.newscombinatorsoap.service;

import com.soursesense.emanuelepicariello.newscombinatorsoap.mapper.HackerNewsMapper;
import com.soursesense.emanuelepicariello.newscombinatorsoap.model.HackerNewsEntity;
import com.soursesense.emanuelepicariello.newscombinatorsoap.news.HackerNews;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@Service
public class HackerNewsService {
    private static final Logger logger = LoggerFactory.getLogger(HackerNewsService.class);


    @Value("${allIdUrlOfHackerNews}")
    private String hackerNews;

    @Value("${UrlofHakerNewsPart1}")
    private String hackerNewsUrlpart1;

    @Value("${UrlofHackerNewsPart2}")
    private String hackerNewsUrlpart2;


    public List<Integer> readAllId() {

        RestTemplate restTemplate = new RestTemplate();
        logger.info("reading all the id of hacker news ");
        return restTemplate.getForObject(hackerNews, List.class);
    }

    public List<HackerNewsEntity> allTheArticlesOfASource() throws InterruptedException, ExecutionException {
        List<Integer> list = readAllId();
        List<HackerNewsEntity> allHackerNewsList;
        RestTemplate restTemplate = new RestTemplate();


        ForkJoinPool customThreadPool = new ForkJoinPool(20);
        logger.info("generating hackerNews article");

        allHackerNewsList = customThreadPool.submit(() -> list.parallelStream().map(p ->
                (restTemplate.getForObject(hackerNewsUrlpart1 + p + hackerNewsUrlpart2, HackerNewsEntity.class)))
                .collect(Collectors.toList())).get();
        logger.info("creation of HackerNewsList");

        CompareDateNews cmp = new CompareDateNews();
        Collections.sort(allHackerNewsList, cmp);


        return allHackerNewsList;
    }

    public List<HackerNews> mappingList() throws ExecutionException, InterruptedException {
        List<HackerNews> hackerNewsList;
        List<HackerNewsEntity> hackerNewsEntityList = allTheArticlesOfASource();
        hackerNewsList = hackerNewsEntityList.parallelStream().map(p ->
                (HackerNewsMapper.INSTANCE.hackerNewsEntityToHackerNews(p))).collect(Collectors.toList());



        return hackerNewsList;
}


    public List<HackerNews> getHackerNews() throws ExecutionException, InterruptedException {
        List<HackerNews> mappingList = mappingList();
        if (mappingList != null)
            return mappingList;
        return new ArrayList<HackerNews>();
    }

}
