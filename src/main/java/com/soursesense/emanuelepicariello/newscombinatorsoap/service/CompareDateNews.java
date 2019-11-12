package com.soursesense.emanuelepicariello.newscombinatorsoap.service;

import com.soursesense.emanuelepicariello.newscombinatorsoap.model.NewsEntity;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class CompareDateNews implements Comparator<NewsEntity> {

	@Override
	public int compare(NewsEntity o1, NewsEntity o2) {
	return o1.getData().compareTo(o2.getData());

}
}
