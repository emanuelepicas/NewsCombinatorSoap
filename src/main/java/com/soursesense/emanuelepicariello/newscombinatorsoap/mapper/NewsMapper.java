package com.soursesense.emanuelepicariello.newscombinatorsoap.mapper;

import com.soursesense.emanuelepicariello.newscombinatorsoap.model.HackerNewsEntity;
import com.soursesense.emanuelepicariello.newscombinatorsoap.model.NyTimesArticleEntity;
import com.soursesense.emanuelepicariello.newscombinatorsoap.news.News;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface NewsMapper {
	
	NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);
	@Mappings({
			@Mapping( source = "title", target = "title"),
			@Mapping( source = "data", target = "time",
					dateFormat = "dd-MM-yyyy HH:mm:ss")})
	News hackerNewsEntityToNews(HackerNewsEntity hackerNews);

	@Mappings({
			@Mapping(target="title", source = "title"),
			@Mapping(target="time", source = "data",
					dateFormat = "dd-MM-yyyy HH:mm:ss")})
	News nyTimesArticleEntityToNews(NyTimesArticleEntity nyTimes);

}