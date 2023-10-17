package com.ipartek;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("usuario_name") != null);

        
        System.out.println("metodo filter, el usuario en sesion: " + isLoggedIn);
        
        if (isLoggedIn || isPublicPath(httpRequest)) {
            // If logged in or accessing a public path, allow the request to proceed
            chain.doFilter(request, response);
        } else {
            // If not logged in and not accessing a public path, redirect to login
            httpResponse.sendRedirect("/canciones_youtube/Inicio");
        }

        System.out.println("Filter chain completed.");
    }
    
    
    
    private boolean isPublicPath(HttpServletRequest httpRequest) {
        String contextPath = httpRequest.getContextPath();
        String path = httpRequest.getRequestURI().substring(contextPath.length());

        // Exclude paths for static resources
        if (path.startsWith("/styles/") || path.startsWith("/images/") || path.startsWith("/scripts/")) {
            return true;
        }

        // Check if path is "/" or empty
        if ("/".equals(path) || path.isEmpty()) {
            return true;
        }

        return "/Inicio".equals(path) || 
               "/formularioCrearUsuario".equals(path) || 
               "/aboutWebsite".equals(path) ||
               "/crearUsuario".equals(path) ||
               "/Login".equals(path);  // Add your login page path here
    }



    // Other Filter methods (init, destroy) can be left empty or not implemented
    @Override
    public void destroy() {
        // Clean-up code, if needed
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed
    }
}
