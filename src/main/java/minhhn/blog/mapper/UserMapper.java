package minhhn.blog.mapper;

import minhhn.blog.dto.UserDto;
import minhhn.blog.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {

  @Override
  @Mapping(target = "createdBy", source = "audit.createdBy")
  @Mapping(target = "createdDate", source = "audit.createdDate")
  UserDto toDto(User entity);

}
