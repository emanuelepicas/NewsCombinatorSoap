package com.soursesense.emanuelepicariello.newscombinatorsoap.mapper;

import com.soursesense.emanuelepicariello.newscombinatorsoap.model.HackerNewsEntity;
import com.soursesense.emanuelepicariello.newscombinatorsoap.news.HackerNews;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel="spring")
public interface HackerNewsMapper {
	
	HackerNewsMapper INSTANCE = Mappers.getMapper(HackerNewsMapper.class);
	@Mappings({
			@Mapping( source = "title", target = "title"),
			@Mapping( source = "data", target = "time",
					dateFormat = "dd-MM-yyyy HH:mm:ss")})
	HackerNews hackerNewsEntityToHackerNews(HackerNewsEntity hackerNews);

}