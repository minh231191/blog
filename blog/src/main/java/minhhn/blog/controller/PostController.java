package minhhn.blog.controller;

import minhhn.blog.dto.PostDto;
import minhhn.blog.dto.PostFilter;
import minhhn.blog.dto.PostPagedDto;
import minhhn.blog.enums.PaginationDirection;
import minhhn.blog.service.PostService;
import minhhn.blog.utils.Authorize;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

  @GetMapping
  public ResponseEntity<PostPagedDto> getPostsPaged(
      @RequestParam Long id, @RequestParam String date, @RequestParam PaginationDirection direction, PostFilter filter) {
    LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
    return ResponseEntity.ok().body(postService.getPostsPaged(id, dateTime, direction, filter));
  }

  @PutMapping
  public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto) {
    return ResponseEntity.ok().body(postService.updatePost(postDto));
  }

  @PostMapping
  public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
    return ResponseEntity.ok().body(this.postService.createPost(postDto));
  }

}
