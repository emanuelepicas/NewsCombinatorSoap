package com.soursesense.emanuelepicariello.newscombinatorsoap.service;

import com.soursesense.emanuelepicariello.newscombinatorsoap.mapper.HackerNewsMapper;
import com.soursesense.emanuelepicariello.newscombinatorsoap.mapper.NyTimesMapper;
import com.soursesense.emanuelepicariello.newscombinatorsoap.model.HackerNewsEntity;
import com.soursesense.emanuelepicariello.newscombinatorsoap.model.NewsInterface;
import com.soursesense.emanuelepicariello.newscombinatorsoap.model.NyTimesArticleEntity;
import com.soursesense.emanuelepicariello.newscombinatorsoap.news.GetHackerNewsResponse;
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
                newsList.add(HackerNewsMapper.INSTANCE.hackerNewsEntityToHackerNews((HackerNewsEntity) s));
            }
            else if(s instanceof NyTimesArticleEntity){
                newsList.add(NyTimesMapper.INSTANCE.nyTimesArticleEntityToNyTimes((NyTimesArticleEntity) s));
            }
        }


        return newsList;
    }

    public GetHackerNewsResponse getAllNewsResponse() throws ExecutionException, InterruptedException, IOException {
        GetHackerNewsResponse response=new GetHackerNewsResponse();
        response.getNews().addAll(mapAllTheArticle());
        return response;

    }




}
