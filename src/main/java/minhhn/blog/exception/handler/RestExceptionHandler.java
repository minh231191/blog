package minhhn.blog.exception.handler;

import io.jsonwebtoken.MalformedJwtException;
import minhhn.blog.exception.JwtTokenException;
import minhhn.blog.exception.RestError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  private static final String UNAUTHORIZED_CODE = "401";
  private static final String BAD_REQUEST_CODE = "400";
  private static final String INTERNAL_SERVER_ERROR_CODE = "500";

  @ExceptionHandler(value = { EntityNotFoundException.class })
  public ResponseEntity<Object> handleInvalidInputException(EntityNotFoundException ex, HttpServletRequest request) {
    return new ResponseEntity<>(this.constructRestError(ex, request, HttpStatus.BAD_REQUEST, BAD_REQUEST_CODE), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = { JwtTokenException.class })
  public ResponseEntity<Object> handleJwtTokenException(JwtTokenException ex, HttpServletRequest request) {
    return new ResponseEntity<>(this.constructRestError(ex, request, HttpStatus.UNAUTHORIZED, UNAUTHORIZED_CODE), HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(value = { MalformedJwtException.class })
  public ResponseEntity<Object> handleMalformedJwtException(MalformedJwtException ex, HttpServletRequest request) {
    return new ResponseEntity<>(this.constructRestError(ex, request, HttpStatus.UNAUTHORIZED, UNAUTHORIZED_CODE), HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(value = { DisabledException.class })
  public ResponseEntity<Object> handleDisabledException(DisabledException ex, HttpServletRequest request) {
    return new ResponseEntity<>(this.constructRestError(ex, request, HttpStatus.UNAUTHORIZED, UNAUTHORIZED_CODE), HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(value = { BadCredentialsException.class })
  public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex, HttpServletRequest request) {
    return new ResponseEntity<>(this.constructRestError(ex, request, HttpStatus.UNAUTHORIZED, UNAUTHORIZED_CODE), HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(value = { Exception.class })
  public ResponseEntity<Object> handleUnexpectedException(Exception ex, HttpServletRequest request) {
    logger.error(ex);
    ex.printStackTrace();
    return new ResponseEntity<>(this.constructRestError(ex, request, HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_CODE), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private RestError constructRestError(Exception ex, HttpServletRequest request, HttpStatus status, String code) {
    return new RestError(status, code, ex, request.getRequestURI());
  }

}
