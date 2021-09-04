package minhhn.blog.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
  private LocalDateTime createdDate;
  private List<TagDto> tags;

}
