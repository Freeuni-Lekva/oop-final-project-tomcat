package ge.edu.freeuni.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*") // Apply the filter to all URLs
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        if (path.startsWith("/login") || path.startsWith("/register")) {
            // Allow access to log in and register pages
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = httpRequest.getSession(false); // Do not create a new session if none exists

        if (session != null && session.getAttribute("currentUserId") != null) {
            // User is logged in, allow access to requested page
            chain.doFilter(request, response);
        } else {
            // User is not logged in, redirect to login page
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
        }
    }

    @Override
    public void destroy() {

    }

}