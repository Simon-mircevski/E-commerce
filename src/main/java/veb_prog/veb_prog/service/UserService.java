package veb_prog.veb_prog.service;

import veb_prog.veb_prog.model.User;

public interface UserService {
    User login(String username, String password);
    User register(String username, String password, String repeatpassword, String name, String surname);
}
