package minhhn.blog.mapper;

import minhhn.blog.dto.PostDto;
import minhhn.blog.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper extends EntityMapper<PostDto, Post> {

  @Mapping(target = "author", source = "user.name")
  @Mapping(target = "authorId", source = "user.id")
  @Mapping(target = "categoryName", source = "category.name")
  PostDto toDto(Post entity);

}
