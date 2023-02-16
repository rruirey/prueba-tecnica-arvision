package es.educaand.rruirey.simplecrud.exception;

/**
 * Exception thrown when a user is not found.
 */
public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(long id) {
    super("Could not find user with id " + id);
  }

}
