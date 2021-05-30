package minhhn.blog.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDisplayDto {

  private Long id;
  private String name;
  private List<CategoryDisplayDto> categories = new ArrayList<>();

}
