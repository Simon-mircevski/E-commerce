package veb_prog.veb_prog.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import veb_prog.veb_prog.model.User;
import veb_prog.veb_prog.model.enumeration.Role;

public interface AuthService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name,
                  String surname, Role role);

}
