package minhhn.blog.service;

import minhhn.blog.dto.CategoryDisplayDto;
import minhhn.blog.model.Category;
import minhhn.blog.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public List<CategoryDisplayDto> findAll() {
    List<Category> childCategories = this.categoryRepository.getAllCategories();
    Map<Long, Category> categoryMap = new HashMap<>();
    for (Category category: childCategories) {
      categoryMap.putIfAbsent(category.getParent().getId(), category.getParent());
      categoryMap.get(category.getParent().getId()).getCategories().add(category);
    }
    return categoryMap.values().stream().map(c -> {
      CategoryDisplayDto dto = new CategoryDisplayDto();
      dto.setId(c.getId());
      dto.setName(c.getName());
      dto.setCategories(c.getCategories().stream().map(e -> {
        CategoryDisplayDto childDto = new CategoryDisplayDto();
        childDto.setId(e.getId());
        childDto.setName(e.getName());
        return childDto;
      }).collect(Collectors.toList()));
      return dto;
    }).collect(Collectors.toList());
  }

}
