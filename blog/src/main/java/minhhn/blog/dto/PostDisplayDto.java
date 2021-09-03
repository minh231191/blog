package minhhn.blog.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDisplayDto {

  private Long id;
  private String title;
  private String subtitle;
  private String status;
  private LocalDateTime createdDate;
  private String author;
  private Long categoryId;
  private String categoryName;

}
