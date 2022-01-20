package veb_prog.veb_prog.service.impl;

import org.springframework.stereotype.Service;
import veb_prog.veb_prog.model.User;
import veb_prog.veb_prog.model.exceptions.*;
import veb_prog.veb_prog.repository.impl.InMemoryUserRepository;
import veb_prog.veb_prog.repository.jpa.UserRepository;
import veb_prog.veb_prog.service.UserService;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if(username==null||username.isEmpty()||password.isEmpty()||password==null){
            throw new MissingArgumentException();
        }
        return userRepository.findByUsernameAndPassword(username,password).orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(String username, String password, String repeatpassword, String name, String surname) {
        return null;
    }
}
