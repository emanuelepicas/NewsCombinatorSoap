package com.soursesense.emanuelepicariello.newscombinatorsoap.endpoint;

import com.soursesense.emanuelepicariello.newscombinatorsoap.news.GetHackerNews;
import com.soursesense.emanuelepicariello.newscombinatorsoap.news.GetHackerNewsResponse;
import com.soursesense.emanuelepicariello.newscombinatorsoap.service.HackerNewsService;
import com.soursesense.emanuelepicariello.newscombinatorsoap.service.NewsService;
import com.soursesense.emanuelepicariello.newscombinatorsoap.service.NyTimesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static jdk.nashorn.internal.objects.Global.print;

@Endpoint
public class NewsEndpoint {

    @Autowired
    HackerNewsService hackerNewsService;
    @Autowired
    NyTimesService nytimesService;
    @Autowired
    NewsService newsService;
@PayloadRoot(namespace = "http://www.newscombinator.com/sample" ,
        localPart = "getHackerNews")
@ResponsePayload
public GetHackerNewsResponse getHackerNewsRequest(@RequestPayload GetHackerNews getHackerNews) throws ExecutionException, InterruptedException, IOException {
    try {
        GetHackerNewsResponse response = new GetHackerNewsResponse();
        if (getHackerNews.getSource().value().equals("hackerNews"))
            response.getNews().addAll(hackerNewsService.getHackerNews());
        else if (getHackerNews.getSource().value().equals("nyTimesNews"))
            response.getNews().addAll(nytimesService.getAllArticleOfNyTimes());
        else if (getHackerNews.getSource().value().equals("all")) {
            response.getNews().addAll(newsService.mapAllTheArticle());
        }
        return response;
    } catch (Exception e) {
        print(e.getCause());
        return new GetHackerNewsResponse();
    }
}

}
