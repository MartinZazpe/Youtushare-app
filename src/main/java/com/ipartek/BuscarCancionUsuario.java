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
import com.ipartek.modelo.dto.Genero;
import com.ipartek.modelo.dto.V_Cancion;


@WebServlet("/BuscarCancionUsuario")
public class BuscarCancionUsuario extends HttpServlet implements DAO_Constantes {
	private static final long serialVersionUID = 1L;
  
	
    public BuscarCancionUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String ruta = VISTA_INICIO;
		
		HttpSession session = request.getSession();
		
		int userId =0;
		
		String searchText = "";
		
		if(session.getAttribute("usuario_id") != null) {
			userId = (Integer)session.getAttribute("usuario_id");
		};
		
			//si ya hay un usuario logeado y guardado en sesion:
			if(session.getAttribute("usuario_name") != null
					&& 
					session.getAttribute("usuario_rol") != null &&
					session.getAttribute("usuario_rol").equals("user")
					) {
				
				searchText = request.getParameter("search");
				
				System.out.println(request.getParameter("search"));
				
				//ejecutar lo necesario para poder enviar en la mochila
				// 3 connect to database
		   DB_Helper db= new DB_Helper();
		   Connection con= db.conectar();
		   
			// 3 (execute method/s stored in dbhelper)
	      List<V_Cancion> cancionesUsuarioPorBusqueda = db.cancionesUsuarioBusqueda(con, searchText, userId);
	      List<Genero> todosLosGenerosRs = db.obtenerTodosGeneros(con);
	      

			// paso 5 desconectar
	       db.desconectar(con);
	       
	       // paso 6 mochila
	   		request.setAttribute("atr_lista_canciones", cancionesUsuarioPorBusqueda);	
	   		request.setAttribute("atr_todosGeneros", todosLosGenerosRs);
	   		
	   		
	  
	   	  ruta = VISTA_MISCANCIONESBUSQUEDA;
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
