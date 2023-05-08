package minhhn.blog.specification;

import minhhn.blog.model.Post;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PostSpecification {

  private PostSpecification() {

  }

  private static final String ID = "id";
  private static final String CATEGORY = "category";
  private static final String USER = "user";

  public static Specification<Post> idLessThan(Long id) {
    return (post, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThan(post.get(ID), id);
  }

  public static Specification<Post> idGreaterThan(Long id) {
    return (post, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(post.get(ID), id);
  }

  public static Specification<Post> categoryIdEqual(Long categoryId) {
    return (post, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(post.get(CATEGORY).get(ID), categoryId);

  }

  public static Specification<Post> userIdEqual(Long userId) {
    return (post, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(post.get(USER).get(ID), userId);
  }

  public static Specification<Post> tagIdEqual(Long tagId) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.join("tags").get(ID), tagId);
  }
}
