package minhhn.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagDto {

  private Long id;
  private String name;
  private String createdBy;
  private LocalDateTime createdDate;

}
