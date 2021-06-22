package minhhn.blog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import minhhn.blog.enums.CategoryStatus;
import minhhn.blog.model.base.Audit;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "bl_category")
@EntityListeners(AuditingEntityListener.class)
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_seq")
  @SequenceGenerator(name = "category_id_seq", allocationSize = 1)
  private Long id;

  private String name;
  private String description;

  @Enumerated(EnumType.STRING)
  private CategoryStatus status;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  private Category parent;

  @OneToMany(mappedBy = "parent", cascade = {CascadeType.MERGE})
  private Set<Category> categories;

  @Embedded
  private Audit audit;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Category category = (Category) o;
    return id.equals(category.id) && name.equals(category.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

}
