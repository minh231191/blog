package minhhn.blog.dto;

import lombok.Data;
import minhhn.blog.enums.CategoryStatus;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDisplayDto {

  private Long id;
  private String name;
  private CategoryStatus status;
  private String createdBy;
  private Instant createdDate;
  private List<CategoryDisplayDto> categories = new ArrayList<>();

}
