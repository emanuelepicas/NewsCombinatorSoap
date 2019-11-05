package com.soursesense.emanuelepicariello.newscombinatorsoap.endpoint;

import com.soursesense.emanuelepicariello.newscombinatorsoap.news.GetHackerNews;
import com.soursesense.emanuelepicariello.newscombinatorsoap.news.GetHackerNewsResponse;
import com.soursesense.emanuelepicariello.newscombinatorsoap.service.HackerNewsService;
import com.soursesense.emanuelepicariello.newscombinatorsoap.service.NytimesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Endpoint
public class NewsEndpoint {
    @Autowired
    HackerNewsService hackerNewsService;
    @Autowired
    NytimesService nytimesService;
@PayloadRoot(namespace = "http://www.newscombinator.com/sample" ,
        localPart = "getHackerNews")
@ResponsePayload
public GetHackerNewsResponse getHackerNewsRequest(@RequestPayload GetHackerNews getHackerNews) throws ExecutionException, InterruptedException, IOException {
    GetHackerNewsResponse response = new GetHackerNewsResponse();
    if(getHackerNews.getSource().value().equals("hackerNews"))
    response.getNews().addAll(hackerNewsService.allTheArticlesOfASource());
    else if(getHackerNews.getSource().value().equals("hackerNews"))
        response.getNews().addAll(nytimesService.getAllArticleOfNyTimes());
    else if(getHackerNews.getSource().value().equals("all")) {
        response.getNews().addAll(hackerNewsService.allTheArticlesOfASource());
        response.getNews().addAll(nytimesService.getAllArticleOfNyTimes());
    }
    return response;
}

}
