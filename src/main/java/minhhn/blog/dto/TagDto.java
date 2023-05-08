package minhhn.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createdDate;

}
