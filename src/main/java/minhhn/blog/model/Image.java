package minhhn.blog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import minhhn.blog.model.base.Audit;
import minhhn.blog.model.base.AuditListener;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "bl_image")
@EntityListeners(AuditListener.class)
public class Image {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_id_seq")
  @SequenceGenerator(name = "image_id_seq", allocationSize = 1)
  private Long id;

  private String link;

  @Embedded
  private Audit audit;

}
