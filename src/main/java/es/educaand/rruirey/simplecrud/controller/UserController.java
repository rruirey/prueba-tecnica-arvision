package es.educaand.rruirey.simplecrud.controller;

import es.educaand.rruirey.simplecrud.exception.UserNotFoundException;
import es.educaand.rruirey.simplecrud.model.User;
import es.educaand.rruirey.simplecrud.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Controller to handle user requests.
 */
@RestController
@RequestMapping("/api")
public class UserController {

  private final UserRepository repository;

  // using constructor injection since field injection is not recommended.
  // https://stackoverflow.com/questions/39890849/what-is-field-injection-and-how-to-avoid-it
  @Autowired
  public UserController(UserRepository repository) {
    this.repository = repository;
  }

  @Operation(summary = "Find all users")
  @GetMapping("/user")
  public Collection<User> findAll() {
    return repository.findAll();
  }

  @Operation(summary = "Find user by its id")
  @GetMapping("/user/{id}")
  public User findById(@PathVariable long id) {
    // if user does not exist, then throw an exception.
    // this exception will be handled by the controller advice.
    return repository.findById(id)
        .orElseThrow(() ->
            new UserNotFoundException(id)
        );
  }

  @Operation(summary = "Create a new user")
  @PostMapping("/user")
  public User create(@RequestBody User user) {
    // save provided user and return it.
    return repository.save(user);
  }

  @Operation(summary = "Update an existing user")
  @PutMapping("/user/{id}")
  public User update(@PathVariable long id, @RequestBody User user) {
    // first check if provided user exists.
    if (!repository.existsById(id)) {
      // if provided user id does not exist,
      // we return a not found response that
      // will be handled by the controller advice.
      throw new UserNotFoundException(id);
    }

    // client may send a user without id, so we set it here
    user.setId(id);

    // save and return the updated user.
    return repository.save(user);
  }

  @Operation(summary = "Delete an existing user")
  @DeleteMapping("/user/{id}")
  public void delete(@PathVariable long id) {
    // delete user by id, if user does not exist,
    // the controller advice will handle the exception.
    repository.deleteById(id);
  }
}
