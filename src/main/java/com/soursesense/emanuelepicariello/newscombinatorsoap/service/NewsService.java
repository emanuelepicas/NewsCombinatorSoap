package com.soursesense.emanuelepicariello.newscombinatorsoap.service;

import com.soursesense.emanuelepicariello.newscombinatorsoap.mapper.NewsMapper;
import com.soursesense.emanuelepicariello.newscombinatorsoap.model.HackerNewsEntity;
import com.soursesense.emanuelepicariello.newscombinatorsoap.model.NewsInterface;
import com.soursesense.emanuelepicariello.newscombinatorsoap.model.NyTimesArticleEntity;
import com.soursesense.emanuelepicariello.newscombinatorsoap.news.GetNewsResponse;
import com.soursesense.emanuelepicariello.newscombinatorsoap.news.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class NewsService {
    @Autowired
    NyTimesService nyTimesService;
    @Autowired
    HackerNewsService hackerNewsService;


    public List<NewsInterface> getAllNews() throws ExecutionException, InterruptedException {
        List<NewsInterface> newsList = new ArrayList<>();
        newsList.addAll(nyTimesService.printNews());
        newsList.addAll(hackerNewsService.allTheArticlesOfASource());
        Collections.sort(newsList);

        return newsList;
    }

    public List<News> mapAllTheArticle() throws ExecutionException, InterruptedException {
        List<News> newsList=new ArrayList<>();
        for(NewsInterface s :getAllNews()){
            if(s instanceof HackerNewsEntity){
                newsList.add(NewsMapper.INSTANCE.hackerNewsEntityToNews((HackerNewsEntity) s));
            }
            else if(s instanceof NyTimesArticleEntity){
                newsList.add(NewsMapper.INSTANCE.nyTimesArticleEntityToNews((NyTimesArticleEntity) s));
            }
        }


        return newsList;
    }

    public GetNewsResponse getAllNewsResponse() throws ExecutionException, InterruptedException, IOException {
        GetNewsResponse response=new GetNewsResponse();
        response.getNews().addAll(mapAllTheArticle());
        return response;

    }




}
