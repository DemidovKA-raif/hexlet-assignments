package exercise.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import static jakarta.persistence.GenerationType.IDENTITY;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"title", "price"})
@Table(name = "products")
public class Product {
    private String title;
    private int price;
    @GeneratedValue(strategy = IDENTITY)
    @Id
    public Long id;


}
// END
