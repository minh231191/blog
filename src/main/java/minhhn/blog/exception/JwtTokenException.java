package minhhn.blog.exception;

import javax.servlet.ServletException;

public class JwtTokenException extends ServletException {

  public JwtTokenException(String message) {
    super(message);
  }

  public JwtTokenException() {
    super();
  }

}
