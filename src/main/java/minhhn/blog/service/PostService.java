package minhhn.blog.service;

import minhhn.blog.dto.PostDto;
import minhhn.blog.mapper.PostMapper;
import minhhn.blog.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

  private final PostRepository postRepository;
  private final PostMapper postMapper;

  public PostService(PostRepository postRepository, PostMapper postMapper) {
    this.postRepository = postRepository;
    this.postMapper = postMapper;
  }

  public PostDto findById(Long id) {
    return postMapper.toDto(postRepository.findById(id).orElseThrow());
  }

}
