package veb_prog.veb_prog.service;

import veb_prog.veb_prog.model.Product;
import veb_prog.veb_prog.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    List<Product> listAllProductsInShoppingCart(Long cartID);
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addProductToShoppingCart(String username,Long productId);
}
