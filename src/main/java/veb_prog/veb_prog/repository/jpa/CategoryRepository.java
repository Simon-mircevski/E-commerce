package veb_prog.veb_prog.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import veb_prog.veb_prog.model.Category;

import java.util.List;

@Repository

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByNameLike(String text);
    void deleteByName(String text);
}
