package minhhn.blog.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class PostDto {

  private Long id;
  private String title;
  private String subtitle;
  private String content;
  private String status;
  private String author;
  private Long authorId;
  private String categoryName;
  private Long categoryId;
  private Instant createdDate;

}
