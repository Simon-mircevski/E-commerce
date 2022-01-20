package veb_prog.veb_prog.repository.impl;

import org.springframework.stereotype.Repository;
import veb_prog.veb_prog.bootstrap.DataHolder;
import veb_prog.veb_prog.model.Category;
import veb_prog.veb_prog.model.Manufacturer;
import veb_prog.veb_prog.model.Product;

import javax.swing.text.html.Option;
import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryProductRepository {
    public List<Product> findAll(){
        return DataHolder.products;
    }

    public Optional<Product> findByID(Long id){
        return DataHolder.products.stream().filter(r->r.getId().equals(id)).findFirst();
    }
    public Optional<Product> findByName(String name){
        return DataHolder.products.stream().filter(r->r.getName().equals(name)).findFirst();
    }

    public Optional<Product> save(String name, Double price, Integer quantity,
                                  Category category, Manufacturer manufacturer) {
        DataHolder.products.removeIf(i->i.getName().equals(name));
        Product product = new Product(name, price, quantity, category, manufacturer);
        DataHolder.products.add(product);
        return Optional.of(product);
    }

    public void deleteByID(Long id){
        DataHolder.products.removeIf(i->i.getId().equals(id));
    }
}
