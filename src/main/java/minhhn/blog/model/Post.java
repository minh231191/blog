package minhhn.blog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import minhhn.blog.enums.PostStatus;
import minhhn.blog.model.base.Audit;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "bl_post")
@EntityListeners(AuditingEntityListener.class)
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_id_seq")
  @SequenceGenerator(name = "post_id_seq", allocationSize = 1)
  private Long id;

  private String title;
  private String subtitle;

  @Column(columnDefinition="text")
  private String content;

  @Enumerated(EnumType.STRING)
  private PostStatus status;

  @ManyToOne
  private Category category;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id")
  private Set<Image> images;

  @ManyToMany
  @JoinTable(
      joinColumns = @JoinColumn(name = "post_id"),
      inverseJoinColumns = @JoinColumn(name = "tag_id")
  )
  private Set<Tag> tags;

  @ManyToOne
  private User user;

  @Embedded
  private Audit audit;

  public void addTag(Tag tag) {
    this.tags.add(tag);
    tag.getPosts().add(this);
  }

  public void removeTag(Tag tag) {
    this.tags.remove(tag);
    tag.getPosts().remove(this);
  }

}
