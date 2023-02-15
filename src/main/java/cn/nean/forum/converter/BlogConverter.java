package cn.nean.forum.converter;


import cn.nean.forum.model.dto.BlogDto;
import cn.nean.forum.model.es.BlogIndexDo;
import cn.nean.forum.model.po.Blog;
import cn.nean.forum.model.vo.BlogVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BlogConverter {

    BlogConverter INSTANCE = Mappers.getMapper(BlogConverter.class);

    BlogVo toBlogVo(Blog blog);

    Blog dtoToBlog(BlogDto blogDto);

    BlogIndexDo toBlogIndexDo(Blog blog);
}
