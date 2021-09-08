package minhhn.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiDto {

  private String path;
  private HttpMethod method;

}
