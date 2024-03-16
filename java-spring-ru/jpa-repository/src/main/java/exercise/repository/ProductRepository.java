package exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import exercise.model.Product;

import org.springframework.data.domain.Sort;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // BEGIN
    List<Product> findByPriceBetween(@Param("min") Integer min, @Param("max") Integer max, Sort sort);
    List<Product> findByPriceGreaterThanEqualOrderByPrice(Integer min, Sort sort);
    List<Product> findByPriceLessThanEqualOrderByPrice(Integer max, Sort sort);
    List<Product> findAllByOrderByPrice(Sort sort);
    // END
}
