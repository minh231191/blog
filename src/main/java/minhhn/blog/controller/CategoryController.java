package minhhn.blog.controller;

import minhhn.blog.dto.CategoryDisplayDto;
import minhhn.blog.dto.CategoryDto;
import minhhn.blog.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping
  public ResponseEntity<List<CategoryDisplayDto>> findAllCategories() {
    return ResponseEntity.ok().body(categoryService.findAll());
  }

  @GetMapping("with-sub")
  public ResponseEntity<List<CategoryDisplayDto>> findAllCategoriesWithSubCategories() {
    return ResponseEntity.ok().body(categoryService.findAllWithSub());
  }

  @GetMapping("{id}")
  public ResponseEntity<CategoryDto> findCategoryById(@PathVariable Long id) {
    return ResponseEntity.ok().body(categoryService.findById(id));
  }

  @GetMapping("available-sub-categories")
  public ResponseEntity<List<CategoryDto>> findAvailableSubCategories() {
    return ResponseEntity.ok().body(categoryService.findAvailableSubCategories());
  }

  @GetMapping("available-sub-categories/{id}")
  public ResponseEntity<List<CategoryDto>> findAvailableSubCategories(@PathVariable Long id) {
    return ResponseEntity.ok().body(categoryService.findAvailableSubCategories(id));
  }

  @PostMapping
  public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
    return ResponseEntity.ok().body(categoryService.createCategory(categoryDto));
  }

  @PutMapping
  public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto) {
    return ResponseEntity.ok().body(categoryService.updateCategory(categoryDto));
  }

}
