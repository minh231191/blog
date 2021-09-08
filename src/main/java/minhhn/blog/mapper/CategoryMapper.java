package minhhn.blog.mapper;

import minhhn.blog.dto.CategoryDto;
import minhhn.blog.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<CategoryDto, Category> {

  @Mapping(target = "createdBy", source = "audit.createdBy")
  @Mapping(target = "createdDate", source = "audit.createdDate")
  CategoryDto toDto(Category entity);

  @Mapping(target = "audit.createdBy", source = "createdBy")
  @Mapping(target = "audit.createdDate", source = "createdDate")
  Category toEntity(CategoryDto dto);

}
