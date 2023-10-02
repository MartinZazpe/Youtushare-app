package com.ipartek;

import java.io.Console;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.modelo.DAO_Constantes;



@WebServlet("/formularioCrearUsuario")
public class formularioCrearUsuario extends HttpServlet implements DAO_Constantes{
	private static final long serialVersionUID = 1L;
       
 
    public formularioCrearUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String ruta = VISTA_INICIO;

		
		HttpSession session = request.getSession();

		//si ya hay un usuario logeado y guardado en sesion:
		if(session.getAttribute("usuario_name") == null) {
		
			System.out.println("el usuario en session " + session.getAttribute("usuario_name"));;
			
		
			
   	  ruta = VISTA_CREARUSUARIOFORMULARIO;
		}
		//si no hay un usuario logeado en sesion no haremos nada e ira a la vista inicio	
		// paso 7
		request.getRequestDispatcher(ruta).forward(request, response);
	}
		
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
