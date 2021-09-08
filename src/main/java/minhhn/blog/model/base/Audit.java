package minhhn.blog.model.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Getter
@Setter
@Embeddable
public class Audit {

  @Column(columnDefinition = "timestamp(3)")
  @CreatedDate
  private LocalDateTime createdDate;

  @CreatedBy
  private String createdBy;

  @Column(columnDefinition = "timestamp(3)")
  @LastModifiedDate
  private LocalDateTime updatedDate;

  @LastModifiedBy
  private String updatedBy;

}
