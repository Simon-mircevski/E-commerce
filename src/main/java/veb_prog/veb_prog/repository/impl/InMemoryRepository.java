package veb_prog.veb_prog.repository.impl;

import org.springframework.stereotype.Repository;
import veb_prog.veb_prog.bootstrap.DataHolder;
import veb_prog.veb_prog.model.Category;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryRepository {
    public List<Category> findAll(){
        return DataHolder.categories;
    }

    public Category save(Category c){
        if(c==null || c.getName()==null || c.getName().isEmpty()){
            return null;
        }
        DataHolder.categories.removeIf(r->r.getName().equals(c.getName()));
        DataHolder.categories.add(c);
        return c;
    }

    public Optional<Category> findByName(String name){
        return DataHolder.categories.stream().filter(r->r.getName().equals(name)).findFirst();
    }

    public Optional<Category> findByID(Long id){
        return DataHolder.categories.stream().filter(r->r.getId().equals(id)).findFirst();
    }

    public List<Category> search(String text){
        return DataHolder.categories.stream().filter(r->r.getName().equals(text)||
                r.getDescription().equals(text)).collect(Collectors.toList());
    }

    public void delete(String text){
        if(text==null){
            return;
        }
        DataHolder.categories.removeIf(r->r.getName().equals(text));
    }
}
