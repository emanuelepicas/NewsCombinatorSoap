package com.soursesense.emanuelepicariello.newscombinatorsoap.mapper;

import com.soursesense.emanuelepicariello.newscombinatorsoap.model.NyTimesArticleEntity;
import com.soursesense.emanuelepicariello.newscombinatorsoap.news.NyTimes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface NyTimesMapper {

    NyTimesMapper INSTANCE = Mappers.getMapper(NyTimesMapper.class);

    @Mappings({
            @Mapping(target="title", source = "title"),
            @Mapping(target="createdDate", source = "data",
                    dateFormat = "dd-MM-yyyy HH:mm:ss")})
    NyTimes nyTimesArticleEntityToNyTimes(NyTimesArticleEntity nyTimes);
}
