package minhhn.blog.mapper;

import minhhn.blog.dto.TagDto;
import minhhn.blog.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TagMapper extends EntityMapper<TagDto, Tag> {

  @Override
  @Mapping(target = "createdBy", source = "audit.createdBy")
  @Mapping(target = "createdDate", source = "audit.createdDate")
  TagDto toDto(Tag entity);

}
