package veb_prog.veb_prog.model.exceptions;

public class MissingArgumentException extends RuntimeException{
    public MissingArgumentException(){
        super("Missing arguments");
    }
}
