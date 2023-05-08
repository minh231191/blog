package minhhn.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

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

  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createdDate;
  private List<TagDto> tags;

}
