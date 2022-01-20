package veb_prog.veb_prog.service.impl;

import org.springframework.stereotype.Service;
import veb_prog.veb_prog.model.Category;
import veb_prog.veb_prog.repository.impl.InMemoryRepository;
import veb_prog.veb_prog.repository.jpa.CategoryRepository;
import veb_prog.veb_prog.service.CategoryService;

import java.util.List;
@Service
public class CategoryServiceImplementation implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImplementation(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Category create(String name, String description) {
        if(name==null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        Category c = new Category(name,description);
        categoryRepository.save(c);
        return c;
    }

    @Override
    public Category update(String name, String description) {
        if(name==null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        Category c = new Category(name,description);
        categoryRepository.save(c);
        return c;
    }

    @Override
    public void delete(String text) {
        categoryRepository.deleteByName(text);
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> searchCategories(String searchText) {
        return categoryRepository.findAllByNameLike(searchText);
    }
}
