package veb_prog.veb_prog.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import veb_prog.veb_prog.model.ShoppingCart;
import veb_prog.veb_prog.model.User;
import veb_prog.veb_prog.model.enumeration.ShoppingCartStatus;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);
}
