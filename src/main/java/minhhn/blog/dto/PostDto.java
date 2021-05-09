package minhhn.blog.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class PostDto {

  private Long id;
  private String title;
  private String subtitle;
  private String content;
  private String status;

  private Set<ImageDto> images = new HashSet<>();

}
