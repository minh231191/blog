package minhhn.blog.repository;

import minhhn.blog.model.Category;

import java.util.List;

public interface CategoryRepositoryCustom {

  List<Category> getAllCategories();

  Category findCategoryById(Long id);

  List<Category> getAvailableSubCategories();

}
