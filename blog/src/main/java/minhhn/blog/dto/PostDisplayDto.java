package minhhn.blog.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class PostDisplayDto {

  private Long id;
  private String title;
  private Instant createdDate;
  private String author;
  private Long categoryId;
  private String categoryName;

}
