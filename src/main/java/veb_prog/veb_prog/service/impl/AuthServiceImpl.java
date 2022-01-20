package veb_prog.veb_prog.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import veb_prog.veb_prog.model.User;
import veb_prog.veb_prog.model.enumeration.Role;
import veb_prog.veb_prog.model.exceptions.InvalidArgumentsException;
import veb_prog.veb_prog.model.exceptions.PasswordsDoNotMatchException;
import veb_prog.veb_prog.model.exceptions.UserNotFoundException;
import veb_prog.veb_prog.model.exceptions.UsernameAlreadyExistsException;
import veb_prog.veb_prog.repository.jpa.UserRepository;
import veb_prog.veb_prog.service.AuthService;
@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role role) {
        if(username==null||username.isEmpty()||password.isEmpty()||password==null){
            throw new InvalidArgumentsException();
        }
        if(!password.equals(repeatPassword)){
            throw new PasswordsDoNotMatchException();
        }
        if(this.userRepository.findByUsername(username).isPresent() || !this.userRepository.findByUsername(username).isEmpty()){
            throw new UsernameAlreadyExistsException(username);
        }

        User user = new User(username,passwordEncoder.encode(password),name,surname,role);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(()-> new UserNotFoundException(s));
    }
}
