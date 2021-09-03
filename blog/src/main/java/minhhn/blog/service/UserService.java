package minhhn.blog.service;

import minhhn.blog.dto.UserDto;
import minhhn.blog.mapper.UserMapper;
import minhhn.blog.model.User;
import minhhn.blog.model.base.Audit;
import minhhn.blog.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    this.passwordEncoder = passwordEncoder;
  }

  public UserDto createUser(UserDto userDto) {
    User toBeSaved = this.userMapper.toEntity(userDto);
    toBeSaved.setAudit(new Audit());
    toBeSaved.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
    return this.userMapper.toDto(this.userRepository.save(toBeSaved));
  }

}
