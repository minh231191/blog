package minhhn.blog.mapper;

import minhhn.blog.dto.CategoryDisplayDto;
import minhhn.blog.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryDisplayMapper extends EntityMapper<CategoryDisplayDto, Category> {

  CategoryDisplayDto toDto(Category entity);

}
