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
	    String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

	    if (session == null || session.getAttribute("usuario_name") == null) {
	        // User not logged in, allow access to public paths handled in servlets
	        chain.doFilter(request, response);
	    } else if (cantAccessLoggedUserPath(path)) {
	        // User logged in but trying to access restricted path, redirect to /Inicio
	        httpResponse.sendRedirect("/canciones_youtube/Inicio");
	    } else {
	        // User logged in and allowed path, proceed
	        chain.doFilter(request, response);
	    }
	}

	private boolean cantAccessLoggedUserPath(String path) {
	    return path.equals("/formularioCrearUsuario") || path.equals("/crearUsuario") || path.equals("/Login");
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
