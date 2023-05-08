package minhhn.blog.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class RestError {

  public RestError(HttpStatus status, String code, Exception exception, String path) {
    this.status = status;
    this.code = code;
    this.message = exception.getMessage();
    this.exceptionClass = exception.getClass().getSimpleName();
    this.path = path;
  }

  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private final LocalDateTime timestamp = LocalDateTime.now();
  private final HttpStatus status;
  private final String code;
  private final String exceptionClass;
  private final String message;
  private final String path;

}
