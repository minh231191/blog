package minhhn.blog.mapper;

import minhhn.blog.dto.ImageDto;
import minhhn.blog.model.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper extends EntityMapper<ImageDto, Image> {

}
