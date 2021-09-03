package minhhn.blog.dto;

import lombok.Data;
import minhhn.blog.enums.CategoryStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDisplayDto {

  private Long id;
  private String name;
  private CategoryStatus status;
  private String createdBy;
  private LocalDateTime createdDate;
  private List<CategoryDisplayDto> categories = new ArrayList<>();

}
