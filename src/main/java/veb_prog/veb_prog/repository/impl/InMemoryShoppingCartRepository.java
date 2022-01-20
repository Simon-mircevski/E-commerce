package veb_prog.veb_prog.repository.impl;

import org.springframework.stereotype.Repository;
import veb_prog.veb_prog.bootstrap.DataHolder;
import veb_prog.veb_prog.model.ShoppingCart;
import veb_prog.veb_prog.model.enumeration.ShoppingCartStatus;

import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryShoppingCartRepository {

    public Optional<ShoppingCart> findById(Long id){
        return DataHolder.shoppingCarts.stream().filter(r->r.getId().equals(id)).findFirst();
    }

    public ShoppingCart save(ShoppingCart shoppingCart){
        DataHolder.shoppingCarts
                .removeIf(r->r.getUser().getUsername().equals(shoppingCart.getUser().getUsername()));
        DataHolder.shoppingCarts.add(shoppingCart);
        return shoppingCart;
    }

    public Optional<ShoppingCart> findByUsernameAndStatus(String user, ShoppingCartStatus status){
        return DataHolder.shoppingCarts.stream().filter(r->r.getUser().getUsername().equals(user) && r.getStatus().equals(status)).findFirst();
    }
}
