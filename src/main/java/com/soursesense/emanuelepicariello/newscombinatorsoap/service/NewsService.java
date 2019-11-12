package com.soursesense.emanuelepicariello.newscombinatorsoap.service;

import com.soursesense.emanuelepicariello.newscombinatorsoap.model.NewsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class NewsService {
    @Autowired
    NyTimesService nyTimesService;
    @Autowired
    HackerNewsService hackerNewsService;


    public List<NewsEntity> getAllNews() throws ExecutionException, InterruptedException {
        List<NewsEntity> newsList=new ArrayList<>();
        CompareDateNews cmp=new CompareDateNews();
        Collections.sort(newsList,cmp);

        return newsList;
    }


}
