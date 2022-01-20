package veb_prog.veb_prog.service.impl;

import org.springframework.stereotype.Service;
import veb_prog.veb_prog.model.Product;
import veb_prog.veb_prog.model.ShoppingCart;
import veb_prog.veb_prog.model.User;
import veb_prog.veb_prog.model.enumeration.ShoppingCartStatus;
import veb_prog.veb_prog.model.exceptions.ProductAlreadyInCart;
import veb_prog.veb_prog.model.exceptions.ProductNotFoundException;
import veb_prog.veb_prog.model.exceptions.ShoppingCartNotFound;
import veb_prog.veb_prog.model.exceptions.UserNotFoundException;
import veb_prog.veb_prog.repository.impl.InMemoryShoppingCartRepository;
import veb_prog.veb_prog.repository.impl.InMemoryUserRepository;
import veb_prog.veb_prog.repository.jpa.ShoppingCartRepository;
import veb_prog.veb_prog.repository.jpa.UserRepository;
import veb_prog.veb_prog.service.ProductService;
import veb_prog.veb_prog.service.ShoppingCartService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ProductService productService;
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
                                   UserRepository userRepository,
                                   ProductService productService){
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productService = productService;
    }
    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartID) {
        if(!this.shoppingCartRepository.findById(cartID).isPresent())
            throw new ShoppingCartNotFound(cartID);

        return this.shoppingCartRepository.findById(cartID).get().getProducts();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        return this.shoppingCartRepository
                .findByUserAndStatus(user,ShoppingCartStatus.CREATED).orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(cart);
                });
    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Product product = this.productService.findByID(productId)
                .orElseThrow(()-> new ProductNotFoundException(productId));
        if(shoppingCart.getProducts()
                .stream().filter(i->i.getId().equals(productId))
                .collect(Collectors.toList()).size()>0)
            throw new ProductAlreadyInCart(productId,username);
        shoppingCart.getProducts().add(product);
        return this.shoppingCartRepository.save(shoppingCart);
    }
}
