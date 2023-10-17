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

import com.google.gson.Gson;
import com.ipartek.modelo.DAO_Constantes;
import com.ipartek.modelo.DB_Helper;
import com.ipartek.modelo.dto.Cancion;
import com.ipartek.modelo.dto.V_Cancion;
// import com.mysql.cj.Session;



@WebServlet("/Cargar")
public class Cargar extends HttpServlet implements DAO_Constantes  {
	private static final long serialVersionUID = 1L;
       
  
    public Cargar() {
        super();
        // TODO Auto-generated constructor stub
    }
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
	String ruta = VISTA_INICIO;

	
		HttpSession session = request.getSession();

		//si ya hay un usuario logeado y guardado en sesion:
		if(session.getAttribute("usuario_name") != null && 
				session.getAttribute("usuario_rol") != null &&
				session.getAttribute("usuario_rol").equals("user")
				)  {
			
			//ejecutar lo necesario para poder enviar en la mochila
			// 3 connect to database
	   DB_Helper db= new DB_Helper();
	   Connection con= db.conectar();
	   
		// 3 (execute method/s stored in dbhelper)
      List<V_Cancion> todasCancionesRs = db.obtenerTodasCanciones(con);
      

		// paso 5 desconectar
       db.desconectar(con);
       
       // paso 6 mochila
       session.setAttribute("atr_lista_canciones", todasCancionesRs);
   		request.setAttribute("atr_lista_canciones", todasCancionesRs);	

   		
   		//esto deberia ir a index, lo ponemos en inicio para intentar forzar a tomcat a ir a inicio;
   	  ruta = VISTA_INICIO;
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
