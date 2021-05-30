package minhhn.blog.dto;

import lombok.Data;

@Data
public class PostDto {

  private Long id;
  private String title;
  private String subtitle;
  private String content;
  private String status;

}
