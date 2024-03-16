package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN
    @GetMapping
    public List<Product> showAll(@RequestParam(required = false) Integer min, @RequestParam(required = false) Integer max) {
        Sort sort = Sort.by(Sort.Order.asc("price"));
        if (min != null && max != null) {
           return productRepository.findByPriceBetween(min, max, sort);
        } else if (min != null) {
            return productRepository.findByPriceGreaterThanEqualOrderByPrice(min,sort);
        } else if (max != null) {
            return productRepository.findByPriceLessThanEqualOrderByPrice(max, sort);
        } else {
            return productRepository.findAllByOrderByPrice(sort);
        }
    }
    // END


    @GetMapping(path = "/{id}")
    public Product show(@PathVariable long id) {

        var product =  productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        return product;
    }
}
