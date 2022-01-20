package veb_prog.veb_prog.model.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String username){
        super(String.format("User with id: %d, already exists.",username));
    }
}

