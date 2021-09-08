package minhhn.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageUploadResponse {

  private boolean uploaded;
  private String url;
  private Map<String, String> errors;

}
