package minhhn.blog.controller;

import minhhn.blog.model.Comment;
import minhhn.blog.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/comments")
public class CommentController {

  private final CommentService commentService;


  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @GetMapping
  public ResponseEntity<List<Comment>> findAllComment() {
    return ResponseEntity.ok().body(commentService.findAllComment());
  }

}
