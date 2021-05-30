package minhhn.blog.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import minhhn.blog.model.base.Audit;
import minhhn.blog.model.base.AuditListener;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "bl_category")
@EntityListeners(AuditListener.class)
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_seq")
  @SequenceGenerator(name = "category_id_seq", allocationSize = 1)
  private Long id;

  private String name;
  private String description;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  @JsonManagedReference
  private Category parent;

  @OneToMany(mappedBy = "parent")
  @JsonBackReference
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
