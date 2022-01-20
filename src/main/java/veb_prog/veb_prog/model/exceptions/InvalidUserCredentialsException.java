package veb_prog.veb_prog.model.exceptions;

public class InvalidUserCredentialsException extends RuntimeException{
    public InvalidUserCredentialsException(){
        super("Invalid user credentials");
    }
}
