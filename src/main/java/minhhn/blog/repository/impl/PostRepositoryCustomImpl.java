package minhhn.blog.repository.impl;

import minhhn.blog.dto.PostFilter;
import minhhn.blog.enums.PaginationDirection;
import minhhn.blog.model.Post;
import minhhn.blog.repository.PostRepositoryCustom;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

public class PostRepositoryCustomImpl implements PostRepositoryCustom {

  private static final String GREATER_THAN = ">";
  private static final String LOWER_THAN = "<";
  private static final String ASCENDING = "asc";
  private static final String DESCENDING = "desc";
  private static final String FILTER_EQUAL_QUERY = "(%s = %s) AND ";

  private static final String GET_POST_PAGED_QUERY =
      "SELECT * FROM (" +
      "SELECT * " +
      "FROM " +
      "(" +
      "   SELECT " +
      "     p.*, " +
      "     ROW_NUMBER() OVER (ORDER BY p.CREATED_DATE %1$s, p.ID %2$s) rn " +
      "   FROM BL_POST p %5$s " +
      "   WHERE %6$s (p.ID %3$s :id OR p.CREATED_DATE %4$s :date)" +
      ") AS all_posts " +
      "WHERE rn <= :postPerPage" +
      ") AS paged_post ORDER BY created_date desc, id desc";

  private static final String INNER_JOIN_POST_TAG = "INNER JOIN BL_POST_TAGS pt on p.ID = pt.POST_ID";

  @PersistenceContext
  private EntityManager entityManager;

  @SuppressWarnings("unchecked")
  @Override
  public List<Post> getPostPaged(int postPerPage, Long id, LocalDateTime date, PaginationDirection direction, PostFilter postFilter) {
    String query;
    if (direction == PaginationDirection.NEXT) {
      query = String.format(
          GET_POST_PAGED_QUERY,
          DESCENDING,
          DESCENDING,
          LOWER_THAN,
          LOWER_THAN,
          postFilter.getTagId() != null ? INNER_JOIN_POST_TAG : StringUtils.EMPTY,
          this.buildFilterQuery(postFilter)
      );
    } else {
      query = String.format(
          GET_POST_PAGED_QUERY,
          ASCENDING,
          ASCENDING,
          GREATER_THAN,
          GREATER_THAN,
          postFilter.getTagId() != null ? INNER_JOIN_POST_TAG : StringUtils.EMPTY,
          this.buildFilterQuery(postFilter)
      );
    }
    Query queryObject = this.entityManager.createNativeQuery(query, Post.class)
        .setParameter("id", id)
        .setParameter("date", date)
        .setParameter("postPerPage", postPerPage);
    return queryObject.getResultList();
  }

  private String buildFilterQuery(PostFilter filter) {
    StringBuilder builder = new StringBuilder();
    if (filter.getCategoryId() != null) {
      builder.append(String.format(FILTER_EQUAL_QUERY, "p.CATEGORY_ID", filter.getCategoryId().toString()));
    }
    if (filter.getUserId() != null) {
      builder.append(String.format(FILTER_EQUAL_QUERY, "p.USER_ID", filter.getUserId().toString()));
    }
    if (filter.getTagId() != null) {
      builder.append(String.format(FILTER_EQUAL_QUERY, "pt.TAG_ID", filter.getTagId().toString()));
    }
    return builder.toString();
  }

}
