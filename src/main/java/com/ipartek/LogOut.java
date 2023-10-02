package com.ipartek;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.modelo.DAO_Constantes;


@WebServlet("/LogOut")
public class LogOut extends HttpServlet implements DAO_Constantes{
	private static final long serialVersionUID = 1L;
       
	
	
    public LogOut() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ruta = VISTA_INICIO;

		
		//Creamos la session
		HttpSession session = request.getSession();
		
		//vemos que el usuario este logeado antes de intentar hacer el log out;
		if(session.getAttribute("usuario_id") != null) {
			session.removeAttribute("usuario_id");
			session.removeAttribute("usuario_name");
			session.removeAttribute("usuario_rol");
		} else {
			System.out.println("the usar has attempted to log out but is not logged in");
		}
		
		request.getRequestDispatcher(ruta).forward(request, response);		
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
