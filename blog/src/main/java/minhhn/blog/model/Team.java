package minhhn.blog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import minhhn.blog.model.base.Audit;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "bl_team")
@EntityListeners(AuditingEntityListener.class)
public class Team {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_id_seq")
  @SequenceGenerator(name = "team_id_seq", allocationSize = 1)
  private Long id;

  private String name;

  @ManyToOne
  private User teamLead;

  @Embedded
  private Audit audit;

}
