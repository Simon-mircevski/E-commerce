package veb_prog.veb_prog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import veb_prog.veb_prog.model.User;
import veb_prog.veb_prog.model.enumeration.Role;
import veb_prog.veb_prog.model.exceptions.InvalidArgumentsException;
import veb_prog.veb_prog.model.exceptions.PasswordsDoNotMatchException;
import veb_prog.veb_prog.service.AuthService;
import veb_prog.veb_prog.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;
    private final AuthService authService;

    public RegisterController(UserService userService,
                              AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent","register");
        return "master-template";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam Role role) {
        try{
            this.authService.register(username, password, repeatedPassword, name, surname, role);
            return "redirect:/login";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}
