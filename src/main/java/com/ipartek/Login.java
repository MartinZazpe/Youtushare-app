package com.ipartek;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.modelo.DAO_Constantes;
import com.ipartek.modelo.DB_Helper;
import com.ipartek.modelo.dto.Cancion;
import com.ipartek.modelo.dto.Usuario;
import com.ipartek.modelo.dto.V_Cancion;
import com.ipartek.modelo.dto.V_Usuario;
// import com.mysql.cj.Session;


@WebServlet("/Login")
public class Login extends HttpServlet implements DAO_Constantes {
	private static final long serialVersionUID = 1L;
       

    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean error = false;
		String fieldErrors = "";
		
		//intentos de login
		int intentos=0;
		
		String ruta = "error404.jsp";
		
		// 1 y 2 recibir y chequear
		String userUsername = "";
		String userPassword = "";
		
		HttpSession session = request.getSession();
		if(session.getAttribute("usuario_name") == null) {

		
		// check user
		if (request.getParameter("user") != null) {
			userUsername = request.getParameter("user").trim();
		} else {
			error = true;
			fieldErrors = "user can't be received by servlet.";
		}
		
		//check password
		if(request.getParameter("password") != null) {
			userPassword = request.getParameter("password");
		} else {
			error = true;
			fieldErrors += "password can't be received by servlet.";
		}
		

		if(error == false && fieldErrors == "") {
		
	        // 1 create SP, get DTOs we receive.

				// 2 layout data to DTOs

				// 3 connect to database
		DB_Helper db= new DB_Helper();
		Connection con= db.conectar();

				// 3 (execute method/s stored in dbhelper)
		List<V_Usuario> loginRs  = db.checkLogin(con, userUsername, userPassword);
		List<V_Cancion> todasCancionesRs = db.obtenerTodasCanciones(con);
		
		
		System.out.println(loginRs + " this is the login result");
		
		
			//intentos de login
			if(session.getAttribute("s_intentos")!= null || loginRs.isEmpty()) {
				//aqui entra cuando no esta creado
				intentos=(int)session.getAttribute("s_intentos")+1;
				session.setAttribute("s_intentos", intentos);
			} else {
				session.setAttribute("s_intentos", 1);
			}
		

		// paso 5 desconectar
		db.desconectar(con);

        // paso 6 mochila
		request.setAttribute("atr_lista_canciones", todasCancionesRs);	

			  // paso 7 redirection & session
    if(loginRs.size() == 1) {

//guardar el usuario en sesión
	V_Usuario[] loginRsToArray = loginRs.toArray(new V_Usuario[0]);
	session.setAttribute("usuario_id" , loginRsToArray[0].getId());
	session.setAttribute("usuario_name", loginRsToArray[0].getUsername());
	session.setAttribute("usuario_rol", loginRsToArray[0].getUser_rol());

	session.setAttribute("s_intentos", null);
	
	if(session.getAttribute("usuario_rol").equals("user") || session.getAttribute("usuario_rol").equals("admin") ) {
		ruta = VISTA_INDEX;
	}
	else {
		ruta = VISTA_INICIO;
	}
} 
    
		  
else {
	System.out.println("there was an error with db result. Users found are none or too many:  " + loginRs);
    ruta = VISTA_INICIO;
}
		} 

		//redirección final
		}else {
			
			DB_Helper db= new DB_Helper();
			Connection con= db.conectar();
			   List<V_Cancion> todasCancionesRs = db.obtenerTodasCanciones(con);
				db.desconectar(con);

		   		request.setAttribute("atr_lista_canciones", todasCancionesRs);
			
		
			 ruta = VISTA_INDEX;
		}
		
	
		
		if(intentos <= 100) {
			request.getRequestDispatcher(ruta).forward(request, response);
		} else {
			response.sendRedirect("https://www.google.es/");
		}

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
