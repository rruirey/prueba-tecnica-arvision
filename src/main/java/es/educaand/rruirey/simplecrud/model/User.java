package es.educaand.rruirey.simplecrud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotBlank
  @Column(nullable = false)
  private String name;

  @NotBlank
  @Column(nullable = false)
  private String surname;

  @Min(0)
  private int age;

}
