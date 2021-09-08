package minhhn.blog.repository;

import minhhn.blog.dto.PostFilter;
import minhhn.blog.enums.PaginationDirection;
import minhhn.blog.model.Post;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepositoryCustom {

  List<Post> getPostPaged(int postPerPage, Long id, LocalDateTime date, PaginationDirection direction, PostFilter postFilter);

}
