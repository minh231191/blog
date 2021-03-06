package minhhn.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import minhhn.blog.enums.CategoryStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

  private Long id;
  private String name;
  private String description;
  private CategoryStatus status;
  private String createdBy;
  private LocalDateTime createdDate;
  private List<CategoryDto> categories = new ArrayList<>();

}
