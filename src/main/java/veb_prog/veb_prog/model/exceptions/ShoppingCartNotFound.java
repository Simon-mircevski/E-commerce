package veb_prog.veb_prog.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import veb_prog.veb_prog.model.enumeration.ShoppingCartStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShoppingCartNotFound extends RuntimeException{
    public ShoppingCartNotFound(Long id){
        super(String.format("Shopping cart with id: %s was not found",id));
    }
}
