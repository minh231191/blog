package minhhn.blog.service;

import minhhn.blog.dto.PostDisplayDto;
import minhhn.blog.dto.PostDto;
import minhhn.blog.dto.PostFilter;
import minhhn.blog.dto.PostPagedDto;
import minhhn.blog.enums.PaginationDirection;
import minhhn.blog.mapper.PostDisplayMapper;
import minhhn.blog.mapper.PostMapper;
import minhhn.blog.model.Post;
import minhhn.blog.repository.PostRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static minhhn.blog.specification.PostSpecification.*;

@Service
public class PostService {

  private final PostRepository postRepository;
  private final PostMapper postMapper;
  private final PostDisplayMapper postDisplayMapper;

  public PostService(PostRepository postRepository, PostMapper postMapper, PostDisplayMapper postDisplayMapper) {
    this.postRepository = postRepository;
    this.postMapper = postMapper;
    this.postDisplayMapper = postDisplayMapper;
  }

  public PostDto findById(Long id) {
    return postMapper.toDto(postRepository.findById(id).orElseThrow());
  }

  public List<PostDisplayDto> getPostsList(Long id, LocalDateTime date, PaginationDirection direction, PostFilter filter) {
    return postDisplayMapper.toDtoList(postRepository.getPostPaged(5, id, date, direction, filter));
  }

  public PostPagedDto getPostsPaged(Long id, LocalDateTime date, PaginationDirection direction, PostFilter filter) {
    PostPagedDto postPagedDto = new PostPagedDto();
    List<PostDisplayDto> posts = this.getPostsList(id, date, direction, filter);
    postPagedDto.setPosts(posts);
    postPagedDto.setFirstPage(!this.existsByIdLowerThan(posts.get(0).getId(), filter));
    int postSize = posts.size();
    if (postSize < 5) {
      postPagedDto.setLastPage(true);
    } else {
      postPagedDto.setLastPage(!this.existsByIdGreaterThan(posts.get(postSize- 1).getId(), filter));
    }
    return postPagedDto;
  }

  public boolean existsByIdLowerThan(Long id, PostFilter postFilter) {
    Specification<Post> postSpecification = Specification.where(idLessThan(id));
    if (postFilter.getCategoryId() != null) {
      postSpecification = postSpecification.and(Specification.where(categoryIdEqual(postFilter.getCategoryId())));
    }
    if (postFilter.getUserId() != null) {
      postSpecification = postSpecification.and(Specification.where(userIdEqual(postFilter.getUserId())));
    }
    return postRepository.count(postSpecification) > 0;
  }

  public boolean existsByIdGreaterThan(Long id, PostFilter postFilter) {
    Specification<Post> postSpecification = Specification.where(idGreaterThan(id));
    if (postFilter.getCategoryId() != null) {
      postSpecification = postSpecification.and(Specification.where(categoryIdEqual(postFilter.getCategoryId())));
    }
    if (postFilter.getUserId() != null) {
      postSpecification = postSpecification.and(Specification.where(userIdEqual(postFilter.getUserId())));
    }
    return postRepository.count(postSpecification) > 0;
  }

}
