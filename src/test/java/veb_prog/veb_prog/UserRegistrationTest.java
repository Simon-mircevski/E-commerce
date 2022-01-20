package veb_prog.veb_prog;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import veb_prog.veb_prog.model.User;
import veb_prog.veb_prog.model.enumeration.Role;
import veb_prog.veb_prog.repository.jpa.UserRepository;
import veb_prog.veb_prog.service.impl.AuthServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private AuthServiceImpl service;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        User user = new User("username","password","name","surname", Role.ROLE_USER);
        Mockito.when(this.userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(this.passwordEncoder.encode(Mockito.anyString())).thenReturn("password");

        this.service = Mockito.spy(new AuthServiceImpl(this.userRepository,this.passwordEncoder));
    }

    @Test
    public void testSuccessRegister(){
        User user = this.service.register("username","password","password","name","surname",Role.ROLE_USER);
        Mockito.verify(this.service).register("username","password","password","name","surname",Role.ROLE_USER);
        Assert.assertNotNull("User is null",user);
        Assert.assertEquals("name do not match","name", user.getName());
        Assert.assertEquals("Password do not match", "password",user.getPassword());
        Assert.assertEquals("surname do no match", "surname",user.getSurname());
        Assert.assertEquals("role do not match",Role.ROLE_USER, user.getRole());
    }
}
