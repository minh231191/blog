package minhhn.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDisplayDto {

  private Long id;
  private String title;
  private String subtitle;
  private String status;

  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createdDate;
  private String author;
  private Long categoryId;
  private String categoryName;

}
