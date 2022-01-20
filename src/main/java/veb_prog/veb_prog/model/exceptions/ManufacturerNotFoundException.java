package veb_prog.veb_prog.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ManufacturerNotFoundException extends RuntimeException{
    public ManufacturerNotFoundException(Long id){
        super(String.format("Manufacturer with ID %d not found",id));
    }
}
