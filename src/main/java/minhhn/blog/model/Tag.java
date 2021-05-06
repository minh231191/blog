package minhhn.blog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import minhhn.blog.model.base.Audit;
import minhhn.blog.model.base.AuditListener;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "bl_tag")
@EntityListeners(AuditListener.class)
public class Tag {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_id_seq")
  @SequenceGenerator(name = "tag_id_seq", allocationSize = 1)
  private Long id;

  private String name;

  @ManyToMany(mappedBy = "tags")
  Set<Post> posts;

  @Embedded
  private Audit audit;

}