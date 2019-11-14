package com.soursesense.emanuelepicariello.newscombinatorsoap.endpoint;

import com.soursesense.emanuelepicariello.newscombinatorsoap.news.GetHackerNews;
import com.soursesense.emanuelepicariello.newscombinatorsoap.news.GetHackerNewsResponse;
import com.soursesense.emanuelepicariello.newscombinatorsoap.news.NewsType;
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

    @PayloadRoot(namespace = "http://www.newscombinator.com/sample",
            localPart = "getHackerNews")
    @ResponsePayload
    public GetHackerNewsResponse getHackerNewsRequest(@RequestPayload GetHackerNews request) throws ExecutionException, InterruptedException, IOException {
        NewsType newsType = request.getSource();

        GetHackerNewsResponse response = new GetHackerNewsResponse();

        switch (newsType) {
            case HACKER_NEWS:
                 return this.hackerNewsService.getHackerNewsResponse();
            case NY_TIMES_NEWS:
                return this.nytimesService.getNyTimesResponse();
            case ALL: {
                return this.newsService.getAllNewsResponse();
            }
            default:
                return null;
        }

    }
}


