package veb_prog.veb_prog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import veb_prog.veb_prog.model.User;
import veb_prog.veb_prog.model.exceptions.InvalidUserCredentialsException;
import veb_prog.veb_prog.service.AuthService;
import veb_prog.veb_prog.service.UserService;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;
    private final AuthService authService;

    public LoginController(UserService userService,
                           AuthService authService){
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping
    public String getLoginPage(Model model){
        model.addAttribute("bodyContent", "login");
        return "master-template";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model){
        User user = null;
        try{
            user = this.userService.login(request.getParameter("username"), request.getParameter("password"));
            request.getSession().setAttribute("user", user);
            return "redirect:/products";
        }
        catch(InvalidUserCredentialsException exception){
            model.addAttribute("hasErrors", true);
            model.addAttribute("error", exception.getMessage());
            return "login";
        }
    }
}
