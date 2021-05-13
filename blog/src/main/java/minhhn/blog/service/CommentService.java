package minhhn.blog.service;

import minhhn.blog.model.Comment;
import minhhn.blog.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

  private final CommentRepository repository;

  public CommentService(CommentRepository repository) {
    this.repository = repository;
  }

  public List<Comment> findAllComment() {
    return repository.findAll();
  }

}
