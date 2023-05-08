package minhhn.blog.service;

import io.jsonwebtoken.lang.Collections;
import minhhn.blog.dto.PostDisplayDto;
import minhhn.blog.dto.PostDto;
import minhhn.blog.dto.PostFilter;
import minhhn.blog.dto.PostPagedDto;
import minhhn.blog.enums.PaginationDirection;
import minhhn.blog.enums.PostStatus;
import minhhn.blog.mapper.PostDisplayMapper;
import minhhn.blog.mapper.PostMapper;
import minhhn.blog.mapper.TagMapper;
import minhhn.blog.model.Category;
import minhhn.blog.model.Post;
import minhhn.blog.model.Tag;
import minhhn.blog.model.base.Audit;
import minhhn.blog.repository.PostRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static minhhn.blog.specification.PostSpecification.*;

@Service
public class PostService {

  private static final int POST_PER_PAGE = 10;

  private final PostRepository postRepository;
  private final PostMapper postMapper;
  private final PostDisplayMapper postDisplayMapper;
  private final TagMapper tagMapper;

  public PostService(PostRepository postRepository, PostMapper postMapper, PostDisplayMapper postDisplayMapper, TagMapper tagMapper) {
    this.postRepository = postRepository;
    this.postMapper = postMapper;
    this.postDisplayMapper = postDisplayMapper;
    this.tagMapper = tagMapper;
  }

  public PostDto findById(Long id) {
    return postMapper.toDto(this.postRepository.findById(id).orElseThrow());
  }

  public List<PostDisplayDto> getPostsList(Long id, LocalDateTime date, PaginationDirection direction, PostFilter filter) {
    return postDisplayMapper.toDtoList(this.postRepository.getPostPaged(POST_PER_PAGE, id, date, direction, filter));
  }

  public PostPagedDto getPostsPaged(Long id, LocalDateTime date, PaginationDirection direction, PostFilter filter) {
    PostPagedDto postPagedDto = new PostPagedDto();
    List<PostDisplayDto> posts = this.getPostsList(id, date, direction, filter);
    if (Collections.isEmpty(posts)) {
      return postPagedDto;
    }
    postPagedDto.setPosts(posts);
    postPagedDto.setFirstPage(!this.existsByIdGreaterThan(posts.get(0).getId(), filter));
    int postSize = posts.size();
    if (postSize < POST_PER_PAGE) {
      postPagedDto.setLastPage(true);
    } else {
      postPagedDto.setLastPage(!this.existsByIdLowerThan(posts.get(postSize- 1).getId(), filter));
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
    if (postFilter.getTagId() != null) {
      postSpecification = postSpecification.and(Specification.where(tagIdEqual(postFilter.getTagId())));
    }
    return this.postRepository.count(postSpecification) > 0;
  }

  public boolean existsByIdGreaterThan(Long id, PostFilter postFilter) {
    Specification<Post> postSpecification = Specification.where(idGreaterThan(id));
    if (postFilter.getCategoryId() != null) {
      postSpecification = postSpecification.and(Specification.where(categoryIdEqual(postFilter.getCategoryId())));
    }
    if (postFilter.getUserId() != null) {
      postSpecification = postSpecification.and(Specification.where(userIdEqual(postFilter.getUserId())));
    }
    if (postFilter.getTagId() != null) {
      postSpecification = postSpecification.and(Specification.where(tagIdEqual(postFilter.getTagId())));
    }
    return this.postRepository.count(postSpecification) > 0;
  }

  public PostDto updatePost(PostDto postDto) {
    Post fromDb = this.postRepository.getOne(postDto.getId());
    fromDb.setStatus(PostStatus.valueOf(postDto.getStatus()));
    fromDb.setTitle(postDto.getTitle());
    fromDb.setSubtitle(postDto.getSubtitle());
    fromDb.setContent(postDto.getContent());
    if (postDto.getCategoryId() != null) {
      Category category = new Category();
      category.setId(postDto.getCategoryId());
      fromDb.setCategory(category);
    }
    List<Tag> postTag = this.tagMapper.toEntityList(postDto.getTags());
    postTag.forEach(fromDb::addTag);
    return this.postMapper.toDto(this.postRepository.save(fromDb));
  }
  
  public PostDto createPost(PostDto postDto) {
    Post toBeSaved = this.postMapper.toEntity(postDto);
    if (postDto.getCategoryId() != null) {
      Category category = new Category();
      category.setId(postDto.getCategoryId());
      toBeSaved.setCategory(category);
    }
    toBeSaved.setStatus(PostStatus.APPROVED);
    toBeSaved.setAudit(new Audit());
    return this.postMapper.toDto(this.postRepository.save(toBeSaved));
  }

}
