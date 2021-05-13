package minhhn.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import minhhn.blog.enums.CommentStatus;
import minhhn.blog.model.base.Audit;
import minhhn.blog.model.base.AuditListener;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "bl_comment")
@EntityListeners(AuditListener.class)
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_id_seq")
  @SequenceGenerator(name = "comment_id_seq", allocationSize = 1)
  private Long id;

  @Enumerated(EnumType.STRING)
  private CommentStatus status;

  private String commenter;
  private String content;

  @JsonIgnore
  @ManyToOne
  private Post post;

  @Embedded
  private Audit audit;

}
