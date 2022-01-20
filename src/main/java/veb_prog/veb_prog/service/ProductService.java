package veb_prog.veb_prog.service;

import veb_prog.veb_prog.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findByName(String text);
    Optional<Product> findByID(Long id);
    Optional<Product> save(String name, Double price, Integer quantity, Long categoryID, Long manufacturerID);
    void deleteByID(Long id);
}
