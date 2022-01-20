package veb_prog.veb_prog.service.impl;

import org.springframework.stereotype.Service;
import veb_prog.veb_prog.model.Category;
import veb_prog.veb_prog.model.Manufacturer;
import veb_prog.veb_prog.model.Product;
import veb_prog.veb_prog.model.exceptions.CategoryNotFoundException;
import veb_prog.veb_prog.model.exceptions.ManufacturerNotFoundException;
import veb_prog.veb_prog.repository.impl.InMemoryManufacturerRepository;
import veb_prog.veb_prog.repository.impl.InMemoryProductRepository;
import veb_prog.veb_prog.repository.impl.InMemoryRepository;
import veb_prog.veb_prog.repository.jpa.CategoryRepository;
import veb_prog.veb_prog.repository.jpa.ManufacturerRepository;
import veb_prog.veb_prog.repository.jpa.ProductRepository;
import veb_prog.veb_prog.service.ProductService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              ManufacturerRepository manufacturerRepository,
                              CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findByName(String text) {
        return this.productRepository.findByName(text);
    }

    @Override
    public Optional<Product> findByID(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Product> save(String name, Double price, Integer quantity,
                                  Long categoryID, Long manufacturerID) {
        Category category = this.categoryRepository.findById(categoryID)
                .orElseThrow(() -> new CategoryNotFoundException(categoryID));
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerID)
                .orElseThrow(()->new ManufacturerNotFoundException(manufacturerID));
        this.productRepository.deleteByName(name);
        return Optional.of(this.productRepository.save(new Product(name,price,quantity,category,manufacturer)));
    }

    @Override
    public void deleteByID(Long id) {
        this.productRepository.deleteById(id);
    }
}
