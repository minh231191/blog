package minhhn.blog.mapper;

import minhhn.blog.dto.PostDisplayDto;
import minhhn.blog.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostDisplayMapper extends EntityMapper<PostDisplayDto, Post> {

  @Override
  @Mapping(target = "author", source = "user.name")
  @Mapping(target = "categoryName", source = "category.name")
  @Mapping(target = "categoryId", source = "category.id")
  @Mapping(target = "createdDate", source = "audit.createdDate")
  PostDisplayDto toDto(Post entity);

}
