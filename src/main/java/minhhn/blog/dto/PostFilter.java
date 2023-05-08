package minhhn.blog.dto;

import lombok.Data;

@Data
public class PostFilter {

  private Long categoryId;
  private Long userId;
  private Long tagId;

}
