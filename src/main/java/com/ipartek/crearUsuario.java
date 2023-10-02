package com.ipartek;

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
import com.ipartek.modelo.DB_Helper;
import com.ipartek.modelo.dto.V_Cancion;
import com.ipartek.modelo.dto.V_Usuario;


@WebServlet("/crearUsuario")
public class crearUsuario extends HttpServlet implements DAO_Constantes {
	private static final long serialVersionUID = 1L;
       

    public crearUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fieldErrors = "";
		
		
		String ruta = VISTA_INICIO;
		
	
		HttpSession session = request.getSession();

		//si NO hay un usuario logeado y guardado en sesion:
		if(session.getAttribute("usuario_name") == null
				&& 
				session.getAttribute("usuario_rol") == null 
				) {
			
		
		// 1 y 2 recibir y chequear
		String userUsername = "";
		String userPassword = "";
		String userPasswordCheck = "";
		
		
		// check user
		if (request.getParameter("user") != null && request.getParameter("user").replace(" ", "") != "") {
			userUsername = request.getParameter("user").trim();
			request.setAttribute("username", "");
		} else {
			request.setAttribute("username", "must enter a username");
			fieldErrors = "user can't be received by servlet. ";
		}
		
		//check password
		if(request.getParameter("password") != null  && request.getParameter("password").replace(" ", "") != "") {
			userPassword = request.getParameter("password");
			request.setAttribute("password", "");
		} else {
			request.setAttribute("password", "must enter a password");
			fieldErrors += "password can't be received by servlet. ";
		}
		
		//check password check
		if(request.getParameter("passwordCheck") != null) {
			userPasswordCheck = request.getParameter("passwordCheck");
			
			if(!userPassword.equals(userPasswordCheck)) {
				request.setAttribute("no_matching_password", "Passwords must be the same");
				fieldErrors += "Passwords must be the same. ";
			}
		} else if(userPassword != null && userPasswordCheck != null && userPassword.equals(userPasswordCheck)) {
       //if not null and password match
			//request.setAttribute("no_matching_password", "");
		}


		if(fieldErrors == "") {

			try {
				
				 // 1 create SP, get DTOs we receive.

				// 2 layout data to DTOs

				// 3 connect to database
			DB_Helper db= new DB_Helper();
			Connection con= db.conectar();
				
			// 3 (execute method/s stored in dbhelper)
			List<V_Cancion> todasCancionesRs = db.obtenerTodasCanciones(con);
			String createUserRs = db.crearUsuario(con, userUsername, userPassword);
		
			System.out.println("user create response = " + createUserRs);
			
			//attempt to auto login user.
			if(createUserRs != "") {			
				
			
					// paso 5 desconectar
					db.desconectar(con);

			        // paso 6 mochila
					request.setAttribute("atr_lista_canciones", todasCancionesRs);	
					
					request.setAttribute("usuario_nuevo", "User created. Log in and enjoy!");
			} 	
			else {
		    	request.setAttribute("usuario_nuevo", "");
			}
			} catch (Exception e) {
				System.out.println("errors on db execution: " + e);
			}

		} 
		 else {  
			    ruta=VISTA_CREARUSUARIOFORMULARIO;
		    	System.out.println("Error creating user: " + fieldErrors );
	    }

	} 
		
else {			
			DB_Helper db= new DB_Helper();
			Connection con= db.conectar();
			   List<V_Cancion> todasCancionesRs = db.obtenerTodasCanciones(con);
				db.desconectar(con);

		   		request.setAttribute("atr_lista_canciones", todasCancionesRs);
				
			 ruta = VISTA_INDEX;
				
}
		request.getRequestDispatcher(ruta).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
