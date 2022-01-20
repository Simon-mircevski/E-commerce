package veb_prog.veb_prog.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import veb_prog.veb_prog.model.Category;
import veb_prog.veb_prog.model.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    void deleteByName(String name);
}
