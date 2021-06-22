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
    TypedQuery<Category> query = entityManager.createQuery("FROM Category c LEFT JOIN FETCH c.parent p", Category.class);
    return query.getResultList();
  }

  @Override
  public Category findCategoryById(Long id) {
    TypedQuery<Category> query = entityManager.createQuery("FROM Category c LEFT JOIN FETCH c.parent p WHERE c.id=:id", Category.class);
    query.setParameter("id", id);
    return query.getSingleResult();
  }

  public List<Category> getAvailableSubCategories() {
    TypedQuery<Category> query = entityManager.createQuery(
        "FROM Category c WHERE c.parent IS NULL AND c.id NOT IN " +
            "(SELECT c.parent.id FROM Category c WHERE c.parent.id IS NOT NULL GROUP BY c.parent.id)",
        Category.class);
    return query.getResultList();
  }

}
