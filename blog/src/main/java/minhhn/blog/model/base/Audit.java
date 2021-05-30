package minhhn.blog.model.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Getter
@Setter
@Embeddable
public class Audit {

  private LocalDateTime createdDate;

  private String createdBy;

  private LocalDateTime updatedDate;

  private String updatedBy;

}
