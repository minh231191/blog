package minhhn.blog.model.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Embeddable;
import java.time.Instant;

@Getter
@Setter
@Embeddable
public class Audit {

  @CreatedDate
  private Instant createdDate;

  @CreatedBy
  private String createdBy;

  @LastModifiedDate
  private Instant updatedDate;

  @LastModifiedBy
  private String updatedBy;

}
