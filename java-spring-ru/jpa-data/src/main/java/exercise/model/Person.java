package exercise.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import static jakarta.persistence.GenerationType.IDENTITY;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Getter
@Setter
@Entity
@Table(name = "person")
public class Person {
    @GeneratedValue(strategy = IDENTITY)
    @Id
    public Long id;
    public String firstName;
    public String lastName;


}
// END
