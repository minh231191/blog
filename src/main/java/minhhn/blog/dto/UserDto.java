package minhhn.blog.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserDto {

  private Long id;
  private String name;
  private String username;
  private String password;
  private String createdBy;
  private LocalDateTime createdDate;

}
