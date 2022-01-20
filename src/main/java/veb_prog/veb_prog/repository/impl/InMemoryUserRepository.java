package veb_prog.veb_prog.repository.impl;

import org.springframework.stereotype.Repository;
import veb_prog.veb_prog.bootstrap.DataHolder;
import veb_prog.veb_prog.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryUserRepository {
    public Optional<User> findByUsername(String username){
        return DataHolder.userList.stream().filter(r->r.getUsername().equals(username)).findFirst();
    }

    public Optional<User> findByUsernameAndPassword(String username, String password){
        return DataHolder.userList.stream().filter(r->r.getUsername().equals(username)&&r.getPassword().equals(password)).findFirst();
    }

    public User saveOrUpdate(User user){
        DataHolder.userList.removeIf(r->r.getUsername().equals(user.getUsername()));
        DataHolder.userList.add(user);
        return user;
    }

    public void delete(String username){
        DataHolder.userList.removeIf(r->r.getUsername().equals(username));
    }
}
