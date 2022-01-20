package veb_prog.veb_prog.web;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;
import veb_prog.veb_prog.model.User;
import veb_prog.veb_prog.model.exceptions.InvalidUserCredentialsException;
import veb_prog.veb_prog.model.exceptions.MissingArgumentException;
import veb_prog.veb_prog.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/servlet/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService;
    private final SpringTemplateEngine springTemplateEngine;

    public LoginServlet(UserService userService,SpringTemplateEngine springTemplateEngine){
        this.userService = userService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        springTemplateEngine.process("login.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getParameter("username");
        String password = (String) req.getParameter("password");
        User user = null;
        try {
            user = userService.login(username, password);
        } catch (InvalidUserCredentialsException ex){
            WebContext context = new WebContext(req,resp,req.getServletContext());
            context.setVariable("hasErrors", true);
            context.setVariable("errors", ex.getMessage());
            springTemplateEngine.process("login.html",context, resp.getWriter());
        } catch (MissingArgumentException ex){
            WebContext context = new WebContext(req,resp,req.getServletContext());
            context.setVariable("hasErrors", true);
            context.setVariable("errors", ex.getMessage());
            springTemplateEngine.process("login.html",context, resp.getWriter());
        }
        req.getSession().setAttribute("user",user);
        resp.sendRedirect("/categories");
    }
}
