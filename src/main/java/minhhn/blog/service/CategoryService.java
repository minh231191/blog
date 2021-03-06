package minhhn.blog.service;

import minhhn.blog.dto.CategoryDisplayDto;
import minhhn.blog.dto.CategoryDto;
import minhhn.blog.mapper.CategoryDisplayMapper;
import minhhn.blog.mapper.CategoryMapper;
import minhhn.blog.model.Category;
import minhhn.blog.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryDisplayMapper categoryDisplayMapper;
  private final CategoryMapper categoryMapper;

  public CategoryService(CategoryRepository categoryRepository, CategoryDisplayMapper categoryDisplayMapper, CategoryMapper categoryMapper) {
    this.categoryRepository = categoryRepository;
    this.categoryDisplayMapper = categoryDisplayMapper;
    this.categoryMapper = categoryMapper;
  }

  public List<CategoryDisplayDto> findAll() {
    List<Category> categories = this.categoryRepository.getAllCategories();
    categories.sort(Comparator.comparingLong(Category::getId));
    return this.categoryDisplayMapper.toDtoList(categories);
  }

  public List<CategoryDisplayDto> findAllWithSub() {
    List<Category> childCategories = this.categoryRepository.getAllCategories();
    Map<Long, Category> categoryMap = new HashMap<>();
    for (Category category: childCategories) {
      if (isNotParentCategory(category)) {
        categoryMap.putIfAbsent(category.getParent().getId(), category.getParent());
        categoryMap.get(category.getParent().getId()).getCategories().add(category);
      }
    }
    return categoryMap.values().stream().map(c -> {
      CategoryDisplayDto dto = this.categoryDisplayMapper.toDto(c);
      dto.setCategories(c.getCategories().stream().map(this.categoryDisplayMapper::toDto).collect(Collectors.toList()));
      return dto;
    }).collect(Collectors.toList());
  }

  public CategoryDto findById(Long id) {
    Category category = this.categoryRepository.findById(id).orElseThrow() ;

    return this.categoryMapper.toDto(category);
  }

  public List<CategoryDto> findAvailableSubCategories() {
    return this.categoryMapper.toDtoList(categoryRepository.getAvailableSubCategories());
  }

  public List<CategoryDto> findAvailableSubCategories(Long id) {
    Category category = this.categoryRepository.findCategoryById(id);
    if (this.isNotParentCategory(category)) {
      return new ArrayList<>();
    }
    List<Category> categories = this.categoryRepository.getAvailableSubCategories();
    categories = categories.stream().filter(c -> !c.getId().equals(id)).collect(Collectors.toList());
    return categoryMapper.toDtoList(categories);
  }

  public CategoryDto createCategory(CategoryDto categoryDto) {
    Category saved = this.categoryRepository.save(categoryMapper.toEntity(categoryDto));
    saved.getCategories().forEach(sub -> sub.setParent(saved));
    return categoryMapper.toDto(this.categoryRepository.save(saved));
  }

  public CategoryDto updateCategory(CategoryDto categoryDto) {
    Category category = this.categoryMapper.toEntity(categoryDto);
    Category toBeUpdated = this.categoryRepository.findById(categoryDto.getId()).orElseThrow();
    toBeUpdated.setName(category.getName());
    toBeUpdated.setDescription(category.getDescription());
    toBeUpdated.setStatus(category.getStatus());
    toBeUpdated.getCategories().forEach(c -> c.setParent(null));
    toBeUpdated.getCategories().clear();
    toBeUpdated.getCategories().addAll(category.getCategories());
    toBeUpdated.getCategories().forEach(c -> c.setParent(toBeUpdated));
    return this.categoryMapper.toDto(categoryRepository.save(toBeUpdated));
  }

  private boolean isNotParentCategory(Category category) {
    return category.getParent() != null;
  }

}
