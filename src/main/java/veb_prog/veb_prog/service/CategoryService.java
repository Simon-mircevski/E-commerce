package veb_prog.veb_prog.service;

import veb_prog.veb_prog.model.Category;

import java.util.List;

public interface CategoryService {
    Category create(String name, String description);
    Category update(String name, String description);
    void delete(String text);
    List<Category> listCategories();
    List<Category> searchCategories(String searchText);
}
