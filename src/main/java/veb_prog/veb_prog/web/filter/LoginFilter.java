package veb_prog.veb_prog.web.filter;

import org.springframework.context.annotation.Profile;
import veb_prog.veb_prog.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.LogRecord;
@WebFilter
@Profile("servlet")
public class LoginFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User user = (User)request.getSession().getAttribute("user");

        String path = request.getServletPath();

        if (!"/login".equals(path) && !"/main.css".equals(path) && !"/register".equals(path) && user==null) {
            response.sendRedirect("/login");
        } else {
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
