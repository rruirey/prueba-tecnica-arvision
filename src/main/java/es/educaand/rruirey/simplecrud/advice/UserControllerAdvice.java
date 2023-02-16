package es.educaand.rruirey.simplecrud.advice;

import es.educaand.rruirey.simplecrud.exception.UserNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Controller advice to handle exceptions thrown
 * by the user controller.
 */
@RestControllerAdvice
public class UserControllerAdvice {

  private static final ResponseEntity<Error> NOT_FOUND_ERROR = new Error(
      HttpStatus.BAD_REQUEST,
      "Provided user id does not exist."
  ).build();

  private static final ResponseEntity<Error> UNPROCESSABLE_ENTITY_ERROR = new Error(
      HttpStatus.UNPROCESSABLE_ENTITY,
      "Provided user data does not match the user's schema."
  ).build();

  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<Error> handleUserNotFoundException(UserNotFoundException ex) {
    return new Error(
        HttpStatus.NOT_FOUND,
        ex.getMessage()
    ).build();
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  public ResponseEntity<Error> handleInvalidUserCreationException(DataIntegrityViolationException ex) {
    return UNPROCESSABLE_ENTITY_ERROR;
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  public ResponseEntity<Error> handleInvalidUserCreationException(ConstraintViolationException ex) {
    return UNPROCESSABLE_ENTITY_ERROR;
  }

  @ExceptionHandler(EmptyResultDataAccessException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<Error> handleDeleteUserException(EmptyResultDataAccessException ex) {
    return NOT_FOUND_ERROR;
  }

  /**
   * Record to represent an error response.
   *
   * @param status  the HTTP status code
   * @param message the error message
   */
  record Error(HttpStatus status, String message) {
    ResponseEntity<Error> build() {
      return new ResponseEntity<>(this, status);
    }
  }
}
