package minhhn.blog.model.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Embeddable;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Embeddable
public class Audit {

  @CreatedDate
  private LocalDateTime createdDate;

  @CreatedBy
  private String createdBy;

  @LastModifiedDate
  private LocalDateTime updatedDate;

  @LastModifiedBy
  private String updatedBy;

}
