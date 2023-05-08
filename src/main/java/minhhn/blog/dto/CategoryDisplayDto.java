package minhhn.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createdDate;

  private List<CategoryDisplayDto> categories = new ArrayList<>();

}
