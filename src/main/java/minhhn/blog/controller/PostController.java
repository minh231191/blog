package minhhn.blog.controller;

import minhhn.blog.dto.PostDto;
import minhhn.blog.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/posts")
public class PostController {

  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping("{id}")
  public ResponseEntity<PostDto> findPostById(@PathVariable Long id) {
    return ResponseEntity.ok().body(postService.findById(id));
  }

}
