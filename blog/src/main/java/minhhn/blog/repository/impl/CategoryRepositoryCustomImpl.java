package minhhn.blog.repository.impl;

import minhhn.blog.model.Category;
import minhhn.blog.repository.CategoryRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Category> getAllCategories() {
    TypedQuery<Category> query = entityManager.createQuery("FROM Category c JOIN FETCH c.parent p", Category.class);
    return query.getResultList();
  }

}
