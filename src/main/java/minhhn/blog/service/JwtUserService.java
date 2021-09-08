package minhhn.blog.service;

import minhhn.blog.model.User;
import minhhn.blog.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserService implements UserDetailsService {

  private final UserRepository userRepository;

  public JwtUserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    User usersFromDb = userRepository.findByUsername(username).orElseThrow();
    return new org.springframework.security.core.userdetails.User(usersFromDb.getUsername(), usersFromDb.getPassword(), new ArrayList<>());
  }

}
