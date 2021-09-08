package minhhn.blog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PostPagedDto {

  private boolean isFirstPage;
  private boolean isLastPage;
  private List<PostDisplayDto> posts;

  @JsonProperty("isFirstPage")
  public boolean isFirstPage() {
    return isFirstPage;
  }

  @JsonProperty("isLastPage")
  public boolean isLastPage() {
    return isLastPage;
  }

}
