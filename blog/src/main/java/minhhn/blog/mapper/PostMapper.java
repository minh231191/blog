package minhhn.blog.mapper;

import minhhn.blog.dto.PostDto;
import minhhn.blog.model.Post;
import org.mapstruct.Mapper;

@Mapper(uses = {ImageMapper.class}, componentModel = "spring")
public interface PostMapper extends EntityMapper<PostDto, Post> {

}
