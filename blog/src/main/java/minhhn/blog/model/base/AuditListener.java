package minhhn.blog.model.base;

import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class AuditListener {

  @PrePersist
  public void setCreatedOn(Audit audit) {
    audit.setCreatedDate(LocalDateTime.now());
    audit.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
  }

  @PreUpdate
  public void setUpdatedOn(Audit audit) {
    audit.setUpdatedDate(LocalDateTime.now());
    audit.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
  }

}
